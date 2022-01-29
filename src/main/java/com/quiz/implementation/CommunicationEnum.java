package com.quiz.implementation;

import com.quiz.abstraction.LocalizedEnum;
import lombok.RequiredArgsConstructor;

import java.util.Locale;
import java.util.ResourceBundle;

import static com.quiz.utils.I18nConstants.BUNDLE_NAME;
import static com.quiz.utils.I18nConstants.I18_ENTER_ANSWER;
import static com.quiz.utils.I18nConstants.I18_ENTER_YOUR_NAME;
import static com.quiz.utils.I18nConstants.I18_FROM;
import static com.quiz.utils.I18nConstants.I18_SCORE;
import static com.quiz.utils.I18nConstants.I18_SELECT_NUMBER;
import static com.quiz.utils.I18nConstants.I18_START;

@RequiredArgsConstructor
public enum CommunicationEnum implements LocalizedEnum {
    FROM(I18_FROM),
    ENTER_ANSWER(I18_ENTER_ANSWER),
    ENTER_YOUR_NAME(I18_ENTER_YOUR_NAME),
    START(I18_START),
    SELECT_NUMBER(I18_SELECT_NUMBER),
    SCORE(I18_SCORE);


    private final String name;

    public String getLocalizedMessage(final Locale locale) {
        final ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
        return bundle.getString(this.name);

    }
    }
