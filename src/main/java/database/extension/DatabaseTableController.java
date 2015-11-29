package database.extension;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import classmapper.DevisionContext;
import classmapper.HTMLAttribute;
import classmapper.Mapper;
import database.dao.DAO;
import database.datatypes.Role;

public class DatabaseTableController<T extends DatabaseTable<T>> {
	
	private final DAO<T> dao;
	private final Class<T> clazz;
	private final Role role;
	private final String path;
	private String rolePath;
	private String formName;
	private String idName;
	
	public DatabaseTableController(String path,DAO<T> dao,Class<T> clazz,Role requiredRole,String formName, String idName){
		this.dao = dao;
		this.clazz = clazz;
		this.role = requiredRole;
		this.rolePath = (""+role).toLowerCase();
		this.path = path;
		this.idName = idName;
		this.formName = formName;
		this.idName = idName;
	}
	
	public DatabaseTableController(String path,DAO<T> dao,Class<T> clazz,Role requiredRole, String formName){
		this(path,dao,clazz,requiredRole,formName,"id");
	}
	
	public DatabaseTableController(String path,DAO<T> dao,Class<T> clazz,Role requiredRole){
		this(path,dao,clazz,requiredRole,"form","id");
	}
	
	
	public ModelAndView addGET() {
	  ModelAndView view = new ModelAndView();
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (auth.getAuthorities().toString().contains("ROLE_"+role)) {
		T obj;
		try {
			obj = clazz.newInstance();
			Mapper mapper = new Mapper(dao.getMapper());
			mapper.removeVariable("id");
			contentModel(view, obj, mapper.mapRaw(obj),"/"+rolePath+"/add"+path);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	  }
	  view.setViewName("contentAdd");
	  return view;
	 }
	
	public ModelAndView addPOST(T form, BindingResult result) { 
	  ModelAndView view = new ModelAndView();
	  if(result.hasErrors()){
		  Mapper mapper = dao.getMapper();
		  mapper.removeVariable("id");
		  contentModel(view, form, mapper.mapRaw(form),"/"+rolePath+"/add"+path);
	  }else{
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  if (auth.getAuthorities().toString().contains("ROLE_"+role)) {		  
			try {
				dao.save(form);
				T obj = clazz.newInstance();
				Mapper mapper = dao.getMapper();
				mapper.removeVariable("id");
				contentModel(view, obj, mapper.mapRaw(obj),"/"+rolePath+"/add"+path);
			} catch (InstantiationException | IllegalAccessException | DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
	  }
	  view.setViewName("contentAdd");
	  return view;
	}
	
	public ModelAndView editGET(int id) {
	  ModelAndView view = new ModelAndView();
	  //check if user is login
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (auth.getAuthorities().toString().contains("ROLE_"+role)) {
		try {
			T obj = dao.select(id);
			Mapper mapper = dao.getMapper();
			mapper.removeVariable("id");
			contentModel(view, obj, mapper.mapRaw(obj),"/"+rolePath+"/edit"+path+"-"+id);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  view.setViewName("contentInput");
	  return view;
	}
	
	public ModelAndView editPOST(int id, T form, BindingResult result , Map<String, Object> model)  {
		ModelAndView view = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth.getAuthorities().toString().contains("ROLE_"+role)) {
				try {
					T obj = dao.select(id);
					obj.update(form);
					dao.update(obj);
					
					Mapper mapper = dao.getMapper();
					mapper.removeVariable("id");
					contentModel(view, form, mapper.mapRaw(obj),"/"+rolePath+"/edit"+path+"-"+id);		
				} catch (DatabaseException e) {
					e.printStackTrace();
				}
		}
		view.setViewName("contentInput");
		return view;
	}
	
	public ModelAndView viewsGET()  {
		ModelAndView view = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getAuthorities().toString().contains("ROLE_"+role)) {
			try {
				Mapper mapper = dao.getMapper();
				DevisionContext ctx = new DevisionContext();
				HTMLAttribute attr = new HTMLAttribute();
				attr.addWrapperAttribute("class","table table-bordered table-striped");
				ctx.htmlContext("table", attr);
				ctx.setLastElementKey("Options");
				ctx.setLastElementValue("<button style='text-align:center;' class='con-button' typeC='EDIT'>Edit</button><button style='text-align:center;' class='con-button' typeC='DELETE'>Delete</button>");
				mapper.setContext(ctx);
				List<T> list = dao.selectAll();
				view.addObject("content", mapper.map(list));
				view.setViewName("contentShow");
				view.addObject("ajaxUrl","/ajax"+path);
				view.addObject("typeUrl",rolePath);
				view.addObject("ajaxId", idName);
				view.addObject("baseType",path);
			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return view;
	}
	
	public String ajax (String type, String id){
		try{
			switch(type){
			case "DELETE": 
				dao.delete(id);
				break;
			default: System.out.println("Hackish Way here");break;
			}
		}catch(Exception e){
			return "msg:'Something went wrong'";
		}
		return type.toLowerCase()+"d"+" "+clazz.getName()+" with Id "+id;
	}
	
	public void contentModel(ModelAndView model, DatabaseTable<T> obj, Map<String,String> map, String url){
		model.addObject("contentMap",map);
		model.addObject("contentType",obj.typeMap);
		model.addObject("contentName",obj.nameMap);
		model.addObject("contentObjectList",dao.selectSelects());
		model.addObject("targetURL",url);
		model.addObject(formName, obj);
		  
	}

}
