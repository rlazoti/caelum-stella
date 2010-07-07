package br.com.caelum.stella.boleto.transformer;

import java.util.Calendar;
import java.util.Locale;

/**
 * Classe que serve apenas para centralizar alguns metodos comuns na geração dos
 * boletos.
 *
 * @author Alberto
 *
 */
class BoletoFormatter {

    private static final Locale locale = new Locale("pt","BR");

    private static final String datePattern = "%1$td/%1$tm/%1$tY";

    static String formatDate(final Calendar date) {
        return String.format(datePattern, date);
    }

    static String formatValue(final double value) {
    	return String.format(locale, "%1$,.2f", value);
    }
}
