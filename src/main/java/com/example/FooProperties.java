package com.example;

import java.net.InetAddress;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import lombok.ToString;

@Data
@ConfigurationProperties(prefix="foo")
public class FooProperties {
	private boolean enabled;
	private String username;
	private String password;
	private InetAddress inetAddress;
	private List list;
}
