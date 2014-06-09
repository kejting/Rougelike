//this is the base of the component system
//everythign that's a component extends it.
//
abstract public class CBase{
  protected Entity owner;
  protected CBase(Entity o){
    owner = o;
  }
}