package com.awe.batch.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindException;

import com.awe.backend.persistence.domain.backend.Category;
import com.awe.backend.persistence.domain.backend.Website;
import com.awe.backend.service.CategoryService;
import com.awe.exceptions.NoSuchCategoryInDBException;

public class WebsiteFieldSetMapper implements FieldSetMapper<Website>, BeanFactoryAware{

	private String name;
	private Class<? extends Website> type;
	private BeanFactory beanFactory;
	private CategoryService service;
	
	@Override
	public Website mapFieldSet(FieldSet fieldSet) throws BindException {
		Website result = getBean();
		String[] values = fieldSet.getValues();
		String[] aaa = fieldSet.getNames();
		String bbb = fieldSet.readString("CATEGORIES");
		String[] split = bbb.split(",");
		for (String s:split) {
			Category category = service.findCategoryByName(s.trim());
			if (category == null) {
				throw new NoSuchCategoryInDBException("Category with name " + s + " does not exist!!!");
			}
			
			result.addCategory(category);
		}		
		
		result.setUrl(fieldSet.readString("URL"));
		result.setDescription(fieldSet.readString("DESCRIPTION"));
		result.setVisible(fieldSet.readBoolean("VISIBLE"));
		return result ;
	}

	@SuppressWarnings("unchecked")
	private Website getBean() {
		if (name != null) {
			return (Website) beanFactory.getBean(name);
		}
		try {
			return type.newInstance();
		}
		catch (InstantiationException e) {
			ReflectionUtils.handleReflectionException(e);
		}
		catch (IllegalAccessException e) {
			ReflectionUtils.handleReflectionException(e);
		}
		// should not happen
		throw new IllegalStateException("Internal error: could not create bean instance for mapping.");
	}
	
	public void setService(CategoryService service) {
		this.service = service;
	}

	public void setTargetType(Class<? extends Website> type) {
		this.type = type;
	}
	
	public void setPrototypeBeanName(String name) {
		this.name = name;
	}
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}
}
