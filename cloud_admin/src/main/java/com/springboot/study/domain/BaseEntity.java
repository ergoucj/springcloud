package com.springboot.study.domain;

import lombok.Data;

import java.io.Serializable;
/**
 * @author Administratior
 */
@Data
public class BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    /** 主键id */
    private Long id;
    
    /** 当前页数 */
    private int currentPage = 1;

    
    /** 每页显示数 */
    private int pageSize = 12;
    

}
