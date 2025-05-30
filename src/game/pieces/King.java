package game.pieces;

import game.board.Position;
import game.utils.PieceColor;

public class King extends Piece {
    public King(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        int rowDiff = Math.abs(position.getRow() - newPosition.getRow());
        int colDiff = Math.abs(position.getColumn() - newPosition.getColumn());

        // Короли могут передвигаться на одну клетку в любом направлении.
        boolean isOneSquareMove = rowDiff <= 1 && colDiff <= 1 && !(rowDiff == 0 && colDiff == 0);

        if (!isOneSquareMove) {
            return false; // Ход не находится в пределах одной клетки.
        }

        Piece destinationPiece = board[newPosition.getRow()][newPosition.getColumn()];
        // этот ход действителен, если место назначения пусто или содержит фигуру противника.
        return destinationPiece == null || destinationPiece.getColor() != this.getColor();
    }
}
