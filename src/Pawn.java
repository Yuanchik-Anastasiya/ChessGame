public class Pawn extends Piece {
    public Pawn(PieceColor color, Position position) {
        super(color, position);
    }
    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        int forwardDirection = color == PieceColor.WHITE ? -1 : 1;
        int rowDiff = (newPosition.getRow() - position.getRow()) * forwardDirection;
        int colDiff = newPosition.getColumn() - position.getColumn();

        // Движение вперёд
        if (colDiff == 0 && rowDiff == 1 && board[newPosition.getRow()][newPosition.getColumn()] == null) {
            return true; // Перемещение вперёд на 1 клетку
        }

        // Начальный ход на 2 клетки
        boolean isStartingPosition = (color == PieceColor.WHITE && position.getRow() == 6) ||
                (color == PieceColor.BLACK && position.getRow() == 1);
        if (colDiff == 0 && rowDiff == 2 && isStartingPosition
                && board[newPosition.getRow()][newPosition.getColumn()] == null) {
            // Проверка квадрата между ними на наличие блокирующих элементов
            int middleRow = position.getRow() + forwardDirection;
            if (board[middleRow][position.getColumn()] == null) {
                return true; // Перемещение вперёд на 2 клетки
            }

            // Диагональный захват
            if (Math.abs(colDiff) == 1 && rowDiff == 1 && board[newPosition.getRow()][newPosition.getColumn()] != null &&
                    board[newPosition.getRow()][newPosition.getColumn()].color != this.color) {
                return true; // Захват фигуры противника
            }

            return false;
        }
        return isStartingPosition;
    }
}
