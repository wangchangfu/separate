package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.PostType;
import com.mapscience.modular.system.mapper.PostTypeMapper;
import com.mapscience.modular.system.service.IPostTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 岗位类别表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
@Service
public class PostTypeServiceImpl extends ServiceImpl<PostTypeMapper, PostType> implements IPostTypeService {

}
