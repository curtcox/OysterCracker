package common.ui;

public final class UITextArea
    extends UIComponent
{
    public String text = "";
    public boolean editable;

    public UITextArea() {}

    public UITextArea(String text) {
        this.text = text;
    }
}