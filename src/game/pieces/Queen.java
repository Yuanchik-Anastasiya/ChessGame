package game.pieces;

import game.board.Position;
import game.utils.PieceColor;

public class Queen extends Piece {
    public Queen(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        // Проверьте, совпадает ли новая позиция с текущей
        if (newPosition.equals(this.position)) {
            return false;
        }

        int rowDiff = Math.abs(newPosition.getRow() - this.position.getRow());
        int colDiff = Math.abs(newPosition.getColumn() - this.position.getColumn());

        // Проверьте, нет ли прямолинейного перемещения
        boolean straightLine = this.position.getRow() == newPosition.getRow()
                || this.position.getColumn() == newPosition.getColumn();

        // Проверьте, нет ли смещения по диагонали
        boolean diagonal = rowDiff == colDiff;

        if (!straightLine && !diagonal) {
            return false; // Движение не является ни прямым, ни диагональным
        }

        // Рассчитать направление движения
        int rowDirection = Integer.compare(newPosition.getRow(), this.position.getRow());
        int colDirection = Integer.compare(newPosition.getColumn(), this.position.getColumn());

        //Проверьте, нет ли каких-либо препятствий на пути
        int currentRow = this.position.getRow() + rowDirection;
        int currentCol = this.position.getColumn() + colDirection;
        while (currentRow != newPosition.getRow() || currentCol != newPosition.getColumn()) {
            if (board[currentRow][currentCol] != null) {
                return false; // Путь заблокирован
            }
            currentRow += rowDirection;
            currentCol += colDirection;
        }

        // Ход действителен, если место назначения пусто или содержит фигуру противника
        Piece destinationPiece = board[newPosition.getRow()][newPosition.getColumn()];
        return destinationPiece == null || destinationPiece.getColor() != this.getColor();
    }
}
