public class Knight extends Piece {
    public Knight(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        if (newPosition.equals(this.position)) {
            return false; // Не удается переместиться в прежнее положение
        }

        int rowDiff = Math.abs(this.position.getRow() - newPosition.getRow());
        int colDiff = Math.abs(this.position.getColumn() - newPosition.getColumn());

        // Проверьте, нет ли рисунка перемещения в форме буквы "Г"
        boolean isValidLMove = (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);

        if (!isValidLMove) {
            return false; // Недопустимый ход конем
        }

        // Ход действителен, если целевая клетка пуста или содержит клетку противника.
        Piece targetPiece = board[newPosition.getRow()][newPosition.getColumn()];
        if (targetPiece == null) {
            return true; //Квадрат пуст, ход действителен
        } else {
            return targetPiece.getColor() != this.getColor(); // Может захватить, если это фигура противника
        }
    }
}
