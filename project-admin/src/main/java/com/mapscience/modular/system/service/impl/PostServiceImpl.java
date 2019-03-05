package com.mapscience.modular.system.service.impl;

import com.mapscience.modular.system.model.Post;
import com.mapscience.modular.system.mapper.PostMapper;
import com.mapscience.modular.system.service.IPostService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 岗位表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-03-05
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

}
