package view;

import entity.CellState;

import javax.swing.*;
import java.awt.*;

public final class CellFabric {
    public static JPanel createCell(CellState state){
        JPanel cell = new JPanel();
        cell.setPreferredSize(new Dimension(20,20));
        cell.setBorder(BorderFactory.createEtchedBorder());
        switch (state){
            case BUSY:
                cell.setBackground(Color.RED);
                break;
            case FREE:
                cell.setBackground(Color.ORANGE);
                break;
            case FIGURE:
                cell.setBackground(Color.BLUE);
                break;
        }
        return cell;
    }
    private CellFabric(){}
}
