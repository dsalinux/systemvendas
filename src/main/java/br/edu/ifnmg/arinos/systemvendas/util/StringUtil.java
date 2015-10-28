/**
 * trim = ignorar espaços
 */
package br.edu.ifnmg.arinos.systemvendas.util;

/**
 *
 * @author Danilo
 */
public class StringUtil {

    public static boolean ehVazio(String texto, boolean trim) {
        if (texto != null) {
            if ("".equals(texto)) {
                return true;
            } else if (trim && "".equals(texto.trim())) {
                return true;
            }
            return false;

        }
        return true;
    }

    public static boolean ehVazio(String texto) {
        return ehVazio(texto, true);
    }

    public static boolean naoEhVazio(String texto) {
        return !ehVazio(texto, true);
    }
}
