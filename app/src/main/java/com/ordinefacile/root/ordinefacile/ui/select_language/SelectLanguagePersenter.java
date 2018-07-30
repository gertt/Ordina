package com.ordinefacile.root.ordinefacile.ui.select_language;

import android.content.Context;
import com.ordinefacile.root.ordinefacile.utils.LocaleManager;

/**
 * Created by root on 1/23/18.
 */

public class SelectLanguagePersenter {

    SelectLanguageActivity selectLanguageActivity;
    Context context;

    public SelectLanguagePersenter(SelectLanguageActivity selectLanguageActivity, Context context) {
        this.selectLanguageActivity = selectLanguageActivity;
        this.context = context;

    }

    public void checkRadioButton() {

        String ActualLanguage = LocaleManager.getLanguage(context);
        if(ActualLanguage.contentEquals("en")){
            selectLanguageActivity.setButtonEnChecket();
        }
        if(ActualLanguage.contentEquals("it")){

            selectLanguageActivity.setButtonEnUnChecket();

        }
    }
}
