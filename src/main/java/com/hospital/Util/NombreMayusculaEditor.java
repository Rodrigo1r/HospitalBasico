package com.hospital.Util;

import java.beans.PropertyEditorSupport;

public class NombreMayusculaEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(text.toUpperCase());
    }
}
