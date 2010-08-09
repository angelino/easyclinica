package br.com.easyclinica.validators.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class NotEmpty {

    private static TypeSafeMatcher<String> EMPTY = new TypeSafeMatcher<String>() {

        @Override
        protected void describeMismatchSafely(String item, Description mismatchDescription) {
            mismatchDescription.appendText(" " + item);
        }

        @Override
        protected boolean matchesSafely(String item) {
            return item != null && !item.equals("");
        }

        public void describeTo(Description description) {
            description.appendText(" n‹o deve ser vazio");
        }

    };

    public static TypeSafeMatcher<String> notEmpty() {
        return EMPTY;
    }
}
