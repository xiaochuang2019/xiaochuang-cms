package com.xiaochuang.cms.service;

import java.util.List;

import com.xiaochuang.cms.domain.Category;

public interface CategoryService {

	List<Category> selectsByChannelId(Integer channelId);

}
