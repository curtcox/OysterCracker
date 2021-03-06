package x.json;

import config.ShouldRun;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class JsonMapParserTest {

    @Rule
    public Timeout globalTimeout = new Timeout(1000);

    @Before
    public void setUp() {
        assumeTrue(ShouldRun.X);
    }

    @Test
    public void parse_throws_exception_when_no_closing_curly() throws IOException {
        try {
            parse("{");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("No closing (}) found",e.getMessage());
        }
    }

    @Test
    public void parse_throws_exception_when_first_token_is_not_left_curly_bracket() throws IOException {
        try {
            parse("}");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Expected ({) as first token, but got (})",e.getMessage());
        }
    }

    @Test
    public void parse_throws_exception_when_comma_encountered_with_no_key() throws IOException {
        try {
            parse("{ ,");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Expected (key), but found (,) after ({)",e.getMessage());
        }
    }

    @Test
    public void parse_throws_exception_when_colon_encountered_with_no_key() throws IOException {
        try {
            parse("{ :");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Expected (key), but found (:) after ({)",e.getMessage());
        }
    }

    @Test
    public void parse_throws_exception_when_closing_curly_encountered_with_no_value() throws IOException {
        try {
            parse("{ 'key' : }");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Expected (value), but found (}) after (:)",e.getMessage());
        }
    }

    @Test
    public void empty_json_map() throws IOException {
        assertEquals(new HashMap(),parse("{ }"));
    }

    @Test
    public void pair_of_strings_in_a_map() throws IOException {
        assertEquals(map("fred","wilma"), parse("{'fred':'wilma'}"));
    }

    @Test
    public void string_that_maps_to_a_number() throws IOException {
        assertEquals(map("Bond",7L), parse("{'Bond': 7}"));
        assertEquals(map("Pi",3.141592653589793), parse("{'Pi': 3.141592653589793}"));
    }

    @Test
    public void string_that_maps_to_true() throws IOException {
        JsonMap map =  parse("{'yes': true}");
        assertEquals(1, map.size());
        JsonValue yes = (JsonValue) map.get("yes");
        assertTrue(yes instanceof JsonValue);
        assertTrue(yes.booleanValue());
        assertEquals(map("yes",Boolean.TRUE), parse("{'yes': true}"));
    }

    @Test
    public void string_that_maps_to_false() throws IOException {
        assertEquals(map("no",Boolean.FALSE), parse("{'no': false}"));
    }

    @Test
    public void pair_of_strings_with_spaces_in_a_map() throws IOException {
        assertEquals(map("Mister","Mr."), parse("{ 'Mister' : 'Mr.' }"));
    }

    @Test
    public void pair_of_strings_with_spaces_in_a_list() throws IOException {
        assertEquals(map("Mister","Mr."), parse("{ 'Mister' : 'Mr.' }"));
    }

    @Test
    public void two_pairs_of_strings() throws IOException {
        assertEquals(map("fred","wilma","barney","betty"),
            parse("{'fred':'wilma','barney':'betty'}"));
    }

    @Test
    public void two_pairs_of_strings_with_spaces() throws IOException {
        assertEquals(map("bob","ted","sue","sally"),
                parse("{ 'bob' : 'ted' , 'sue' : 'sally' }"));
    }

    @Test
    public void three_pairs_of_strings() throws IOException {
        assertEquals(map("a","able","b","baker","c","charlie"),
                parse("{'a':'able','b':'baker','c':'charlie'}"));
    }

    @Test
    public void four_pairs_of_strings() throws IOException {
        assertEquals(map("a","able","b","baker","c","charlie","d","delta"),
                parse("{'a':'able','b':'baker','c':'charlie','d':'delta'}"));
    }

    @Test
    public void pair_of_strings_with_url() throws IOException {
        assertEquals(
           map(
               "Vodka","http://absolute/value_page",
               "Spencer","relative_value_page"
           ),
           parse(
               "{",
               "'Vodka':'http://absolute/value_page' ,",
               "'Spencer':'relative_value_page' ",
               "}"
           )
        );
    }

    @Test
    public void multi_level_nested_maps() throws IOException {
        assertEquals(
          map("menu",map("id","file","value","File")),
          parse(
              "{",
              "  'menu' :{",
              "     'id': 'file',",
              "     'value': 'File'",
              "  }",
              "}"
          )
        );
    }

    @Test
    public void map_containing_a_list() throws IOException {
        assertEquals(
                    map("menu",list("file")),
                    parse("{ 'menu' : [ 'file' ] }"
                )
        );
    }

    @Test
    public void map_containing_just_a_list() throws IOException {
        assertEquals(
                map("stuff",list()),
                parse("{ 'stuff' : [ ] }"
                )
        );
    }

    @Test
    public void map_containing_list_of_maps() throws IOException {
        assertEquals(
                map("results",list(map("a","ape"),map("b","bee"))),
                parse("{ 'results' : [ { 'a' : 'ape'},{'b':'bee'} ] }"
                )
        );
    }

    @Test
    public void map_containing_list_of_empty_maps() throws IOException {
        assertEquals(
                map("results",list(map(),map())),
                parse("{ 'results' : [ { } , { } ] }"
                )
        );
    }
    @Test
    public void map_containing_map_of_maps() throws IOException {
        assertEquals(
                map("what",map("the", map("word", "bird"))),
                parse("{ 'what' : { 'the' : { 'word' : 'bird'} } }"
                )
        );
    }

    @Test
    public void map_containing_map_of_2_maps() throws IOException {
        assertEquals(
                map("what",map("the",map("word","bird") ,"make",map("plan","stan") )),
                parse("{ 'what' : { 'the' : { 'word' : 'bird'} ,  'make' : {'plan' : 'stan' }  } }"
                //     1          2         3                1             4                2  3 4
                )
        );
    }


    @Test
    public void map_containing_nested_map() throws IOException {
        assertEquals(
                map("r",
                        map("g",map("o",map()))),
                parse(
                        "{ 'r' : {",
                        "  'g' : { 'o' : { }  },",
                        "} }"
                )
        );
    }

    @Test
    public void map_containing_simple_values_after_nested_map() throws IOException {
        assertEquals(
                map("r",
                        map("g",map("o",map()),"i","c")),
                parse(
                        "{ 'r' : {",
                        "  'g' : { 'o' : { }  } ,",
                        "  'i' : 'c'",
                        "} }"
                )
        );
    }

    @Test
    public void map_containing_list_with_simple_values_after_nested_map() throws IOException {
        assertEquals(
                map("r",
                    list(map("g", map("o", map()), "i", "cu")) ),
                parse(
                      "{ 'r' : [",
                      "            {",
                      "              'g' : { 'o' : { }  } ,",
                      "              'i' : 'cu'",
                      "            }",
                      "] }"
                )
        );
    }

    @Test
    public void map_containing_pair_between_empty_lists() throws IOException {
        assertEquals(
                map("a",list(), "b","boy","c",list()),
                parse("{ 'a' : [], 'b' : 'boy', 'c' : [] }"
                )
        );
    }

    @Test
    public void end_is_index_of_next_token_after_parsing_empty_map() throws IOException {
        assertEquals(",",after("{} ,"));
        assertEquals("}",after("{} }"));
    }

    @Test
    public void end_is_index_of_next_token_after_parsing_map_with_one_item() throws IOException {
        assertEquals(",",after("{'a':'ape'} ,"));
        assertEquals("}",after("{'a':'ape'} }"));
    }

    @Test
    public void end_is_index_of_next_token_after_parsing_map_with_empty_map() throws IOException {
        assertEquals(",",after("{ 'o' : { }  } ,"));
        assertEquals("}",after("{ 'o' : { }  } }"));
    }

    @Test
    public void end_is_index_of_next_token_after_parsing_map_with_map_empty_map() throws IOException {
        assertEquals(",",after("{ 'g' : { 'o' : { }  } } ,"));
        assertEquals("}",after("{ 'g' : { 'o' : { }  } } }"));
    }

    private String after(String json) throws IOException {
        String[] tokens = XJSONParser.split(json);
        JsonMapParser parser = new JsonMapParser(tokens,0);
        parser.parse();
        return tokens[parser.end];
    }

    private static List list(Object... args) {
        return Arrays.asList(args);
    }

    private static Map map(Object...args) {
        return XJSONParserTest.map(args);
    }

    private static JsonMap parse(String... lines) throws IOException {
        String json = JSON(lines);
        String[] tokens = XJSONParser.split(json);
        JsonMapParser parser = new JsonMapParser(tokens,0);
        return parser.parse();
    }

    private static String JSON(String... lines) {
        return XJSONParserTest.JSON(lines);
    }

}