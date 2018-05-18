package cn.edu.nyist.bookMv1.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
/**
 * 
 * @author 閸楁妲煎宄板灠<br>
 * 2015楠烇拷5閺堬拷11閺冿拷 娑撳宕�3:56:16<br>
 *
 * 缁槒顕╅弰锟�:缁剧姵顒滄妯款吇閹懎鍠屾稉瀣）閺堢喕娴嗛幑銏ゆ晩鐠囶垳娈戦梻顕�顣�
 */
public class MyBeanUtils {
	/**
	 * 
	 * @param bean 鐟曚浇顫︾挧瀣拷鑲╂畱JavaBean
	 * @param properties 閸栧懎鎯堥崐鑲╂畱map鐎电钖�
	 * @param dateFormat 婵″倹鐏夐張澶嬫）閺堢喎鍨棁锟界憰浣瑰瘹鐎规碍妫╅張鐔哥壐瀵拷
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void populate(Object bean, Map properties, String dateFormat) {
		ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
		if (dateFormat!=null&&!dateFormat.isEmpty()) {		
			DateTimeConverter dtConverter = new DateConverter();
			dtConverter.setPattern(dateFormat);
			convertUtilsBean.deregister(Date.class);
			convertUtilsBean.register(dtConverter, Date.class);
		}
		BeanUtilsBean beanUtilsBean = new BeanUtilsBean(convertUtilsBean,new PropertyUtilsBean());
		try {
			beanUtilsBean.populate(bean, properties);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	@SuppressWarnings("rawtypes")
	public static void populate(Object bean, Map properties) {
		populate(bean, properties, null);
		
	}
}
