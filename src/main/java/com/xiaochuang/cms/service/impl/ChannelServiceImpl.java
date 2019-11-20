package com.xiaochuang.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaochuang.cms.dao.ChannelMapper;
import com.xiaochuang.cms.domain.Channel;
import com.xiaochuang.cms.service.ChannelService;
@Service
public class ChannelServiceImpl implements ChannelService {
	@Autowired
	private ChannelMapper channelMapper;
	@Override
	public List<Channel> selects() {
		return channelMapper.selects();
	}

}
