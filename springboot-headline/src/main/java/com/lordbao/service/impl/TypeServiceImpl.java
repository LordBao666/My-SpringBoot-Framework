package com.lordbao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lordbao.pojo.Type;
import com.lordbao.service.TypeService;
import com.lordbao.mapper.TypeMapper;
import org.springframework.stereotype.Service;

/**
* @author LordBao
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-09-06 14:38:11
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

}




