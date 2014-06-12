  public enum Direction{
    UP(0, -1), DOWN(0,1), LEFT(-1,0), RIGHT(1,0), NONE(0,0);
    
    public final Position offset;
    private Direction(int x, int y){
      offset = new Position(x,y);
    }
  }