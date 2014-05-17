import java.util.HashMap;

class Entity{
  private HashMap<Class, CBase> components = new HashMap<Class, CBase>();
  public void addComponent(CBase component){
    components.put(component.getClass(), component);
  }
}