import di.ServiceLocator;

import javax.swing.*;


/*

JOÃO PEDRO PASCHOALINI - RA 837759
JOÃO VITOR CHIVITE - RA 768343
WILLIAM RAFAEL PINTO - RA 838747

 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ServiceLocator.getInstance().getLoginView().open();
        });
    }
}