//this is the base of the component system
//everythign that's a component extends it.
//
abstract class CBase{
  protected Entity owner;
  protected CBase(Entity o){
    owner = o;
  }
  public Entity getOwner(){return owner;}
}