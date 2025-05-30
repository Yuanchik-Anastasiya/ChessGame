package game.pieces;

import game.board.Position;
import game.utils.PieceColor;

public class Bishop extends Piece {
    public Bishop(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        int rowDiff = Math.abs(position.getRow() - newPosition.getRow());
        int colDiff = Math.abs(position.getColumn() - newPosition.getColumn());

        if (rowDiff != colDiff) {
            return false; // Движение не является диагональным
        }

        int rowStep = newPosition.getRow() > position.getRow() ? 1 : -1;
        int colStep = newPosition.getColumn() > position.getColumn() ? 1 : -1;
        int steps = rowDiff - 1; // Количество квадратов для проверки на наличие препятствий

        // Проверьте, нет ли препятствий на пути
        for (int i = 1; i <= steps; i++) {
            if (board[position.getRow() + i * rowStep][position.getColumn() + i * colStep] != null) {
                return false; // На пути есть какое-то препятствие
            }
        }

        // Отметьте целевой квадрат для захвата или перемещения в пустой квадрат
        Piece destinationPiece = board[newPosition.getRow()][newPosition.getColumn()];
        if (destinationPiece == null) {
            return true; // Пункт назначения пуст, перемещение допустимо.
        } else if (destinationPiece.getColor() != this.getColor()) {
            return true; // У цели есть фигура противника, захват действителен.
        }

        return false; // В пункте назначения есть фигура того же цвета, ход недопустим.
    }
}
