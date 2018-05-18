package cn.edu.nyist.bookMv1.biz.impl;

import java.util.List;

import cn.edu.nyist.bookMv1.biz.TypeBiz;
import cn.edu.nyist.bookMv1.dao.TypeDao;
import cn.edu.nyist.bookMv1.dao.impl.TypeDaoJdbcImpl;
import cn.edu.nyist.bookMv1.vo.TypeVo;

public class TypeBizImpl implements TypeBiz {

	@Override
	public List<TypeVo> findAllTypes() {
		TypeDao typeDao=new TypeDaoJdbcImpl();
		return typeDao.findAll();
	}

}
