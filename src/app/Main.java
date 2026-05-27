/**
 * INTEGRAÇÃO / MAIN / TESTES = Pietro Bruneli.
 */
package app;

import javax.swing.SwingUtilities;
import view.TelaAluno;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaAluno().setVisible(true));
    }
}
