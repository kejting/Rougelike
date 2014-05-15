//this is the base of the component system
//everythign that's a component extends it.
//
abstract class CBase{
  Entity owner;
  CBase(Entity o){
    owner = o;
  }
}