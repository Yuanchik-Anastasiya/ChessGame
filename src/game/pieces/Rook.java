package game.pieces;

import game.board.Position;
import game.utils.PieceColor;

public class Rook extends Piece {
    public Rook(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        // Ладьи могут перемещаться по вертикали или горизонтали на любое количество клеток.
        // Они не могут перепрыгивать через фигуры.
        if (position.getRow() == newPosition.getRow()) {
            int columnStart = Math.min(position.getColumn(), newPosition.getColumn()) + 1;
            int columnEnd = Math.max(position.getColumn(), newPosition.getColumn());
            for (int column = columnStart; column < columnEnd; column++) {
                if (board[position.getRow()][column] != null) {
                    return false; // На пути есть какое-то препятствие
                }
            }
        } else if (position.getColumn() == newPosition.getColumn()) {
            int rowStart = Math.min(position.getRow(), newPosition.getRow()) + 1;
            int rowEnd = Math.max(position.getRow(), newPosition.getRow());
            for (int row = rowStart; row < rowEnd; row++) {
                if (board[row][position.getColumn()] != null) {
                    return false; // На пути есть какое-то препятствие
                }
            }
        } else {
            return false; // Недопустимый ход ладьей (не по прямой линии)
        }

        // Отметьте целевой квадрат для захвата
        Piece destinationPiece = board[newPosition.getRow()][newPosition.getColumn()];
        if (destinationPiece == null) {
            return true; // The destination is empty, move is valid.
        } else if (destinationPiece.getColor() != this.getColor()) {
            return true; // У цели есть фигура противника, захват действителен.
        }

        return false; // В пункте назначения есть фигура того же цвета, ход недопустим.
    }
}
