/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2p_grupo2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import javafx.util.StringConverter;

/**
 *
 * @author LENOVO
 */
public class SpanishDateConverter extends StringConverter<LocalDate> {
        private final DateFormat dateFormat;

        public SpanishDateConverter() {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ES"));
        }

        @Override
        public String toString(LocalDate date) {
            if (date != null) {
                return dateFormat.format(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            }
            return null;
        }

        @Override
        public LocalDate fromString(String text) {
            if (text != null && !text.isEmpty()) {
                try {
                    Date parsedDate = dateFormat.parse(text);
                    return parsedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
