package c1.ui;

import common.ui.IForm;

/**
 * For creating new FormS.
 * This exists to provide a single spot for Form configuration.
 * See DebugForm.
 * @author Curt
 */
public final class C1FormFactory
    implements IFormFactory
{

    public IForm newForm(String title) {
        return new C1Form(title);
    }

}