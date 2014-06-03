import java.util.HashMap;
//What is this exactly? -Adrian
class Entity{
  private HashMap<Class, CBase> components = new HashMap<Class, CBase>();
  public void addComponent(CBase component){
    components.put(component.getClass(), component);
  }
  public CBase getComponent(Class ob){
    return components.get(ob);
  }
}
