package com.song.attence.util;

import com.song.attence.base.Desc;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CodeUtil {

    public static final String ENTER = "\n";
    public static final String TAB = "    ";

    //创建人信息
    public static final String AUTHOR = "jiansong0720@gmail.com";
    public static final String NOW_DATE = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    //实体类包
    public static final String DOMAIN_PACKAGE = "com.song.attence.domain";

    //生成的类包
    public static final String CONTROLLER_PACKAGE = "com.song.attence.controller";
    public static final String SERVICE_PACKAGE = "com.song.attence.service";
    public static final String REPOSITORY_PACKAGE = "com.song.attence.repository";
    public static final String REQ_PACKAGE = "com.song.attence.controller.req";
    public static final String RES_PACKAGE = "com.song.attence.controller.res";

    //类保存地址（相对项目根目录）
    public static final String BASE_PATH = "src/";

    public static void main(String[] args) {
        String beanName = "Notice";
        String beanDesc = "通知单";

        //实体bean
//        Class<Notice> beanClazz = Notice.class;

        CodeUtil.createRepositoryClass(beanName, beanDesc);
        CodeUtil.createServiceClass(beanName, beanDesc);
        CodeUtil.createControllerClass(beanName, beanDesc);
//        CodeUtil.createAddReqClass(beanClazz, beanName, beanDesc);
//        CodeUtil.createEditReqClass(beanClazz, beanName, beanDesc);
//        CodeUtil.createDetailResClass(beanClazz, beanName, beanDesc);
//        CodeUtil.createPageReqClass(beanName, beanDesc);
//        CodeUtil.createPageResClass(beanClazz, beanName, beanDesc);
    }

    /**
     * 生成Repository
     *
     * @param className 实体名
     * @param describe  实体描述
     */
    public static void createRepositoryClass(String className, String describe) {
        StringBuilder buffer = new StringBuilder();
        //引入包
        buffer.append("package " + REPOSITORY_PACKAGE + ";").append(ENTER);
        buffer.append(ENTER);
        buffer.append("import " + DOMAIN_PACKAGE + "." + className + ";").append(ENTER);
        buffer.append("import org.springframework.data.jpa.repository.JpaRepository;").append(ENTER);
        buffer.append("import org.springframework.data.jpa.repository.JpaSpecificationExecutor;").append(ENTER);
        buffer.append(ENTER);

        //填写作者信息
        authorMessage(describe, buffer);

        //类生成
        buffer.append("public interface " + className + "Repository extends JpaRepository<" + className + ", Long>, JpaSpecificationExecutor<" + className + "> {");
        buffer.append(ENTER);
        buffer.append(ENTER);
        buffer.append("}");
        FileUtil.save(BASE_PATH + REPOSITORY_PACKAGE.replaceAll("\\.", "/") + "/" + className + "Repository.java", buffer.toString());
    }

    /**
     * 生成Service
     *
     * @param className 实体名
     * @param describe  实体描述
     */
    public static void createServiceClass(String className, String describe) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("package " + SERVICE_PACKAGE + ";").append(ENTER);
        buffer.append(ENTER);

        //引入依赖包
        buffer.append("import com.jiuling.controller.response.base.PageRes;").append(ENTER);
        buffer.append("import com.jiuling.constant.Errors;").append(ENTER);
        buffer.append("import com.jiuling.exception.BusinessException;").append(ENTER);
        buffer.append("import " + REQ_PACKAGE + "." + className + "AddReq;").append(ENTER);
        buffer.append("import " + REQ_PACKAGE + "." + className + "PageReq;").append(ENTER);
        buffer.append("import " + RES_PACKAGE + "." + className + "PageRes;").append(ENTER);
        buffer.append("import " + REQ_PACKAGE + "." + className + "EditReq;").append(ENTER);
        buffer.append("import " + RES_PACKAGE + "." + className + "DetailRes;").append(ENTER);
        buffer.append("import " + DOMAIN_PACKAGE + "." + className + ";").append(ENTER);
        buffer.append("import " + REPOSITORY_PACKAGE + "." + className + "Repository;").append(ENTER);
        buffer.append("import org.springframework.stereotype.Service;").append(ENTER);
        buffer.append("import org.springframework.beans.BeanUtils;").append(ENTER);
        buffer.append("import org.springframework.data.domain.Page;").append(ENTER);
        buffer.append("import org.springframework.data.domain.PageRequest;").append(ENTER);
        buffer.append("import javax.annotation.Resource;").append(ENTER);
        buffer.append(ENTER);

        //填写作者信息
        authorMessage(describe, buffer);

        //引入注解
        buffer.append("@Service").append(ENTER);
        buffer.append("public class " + className + "Service {").append(ENTER);
        buffer.append(ENTER);

        buffer.append(TAB).append("@Resource").append(ENTER);
        buffer.append(TAB).append("private ").append(className).append("Repository ").append(StringUtils.uncapitalize(className)).append("Repository;").append(ENTER);
        buffer.append(ENTER);

        //新增
        buffer.append(TAB).append("public Long add" + className + "(" + className + "AddReq req) {").append(ENTER);
        buffer.append(TAB).append(TAB).append(className + " " + StringUtils.uncapitalize(className) + " = new " + className + "();").append(ENTER);
        buffer.append(TAB).append(TAB).append("BeanUtils.copyProperties(req, " + StringUtils.uncapitalize(className) + ");").append(ENTER);
        buffer.append(TAB).append(TAB).append(StringUtils.uncapitalize(className) + " = ").append(StringUtils.uncapitalize(className) + "Repository.save(" + StringUtils.uncapitalize(className) + ");").append(ENTER);
        buffer.append(TAB).append(TAB).append("return " + StringUtils.uncapitalize(className) + ".getId();").append(ENTER);
        buffer.append(TAB).append("}").append(ENTER);
        buffer.append(ENTER);

        //编辑
        buffer.append(TAB).append("public void edit" + className + "(" + className + "EditReq req) {").append(ENTER);
        buffer.append(TAB).append(TAB).append(className + " " + StringUtils.uncapitalize(className) + " = " + StringUtils.uncapitalize(className) + "Repository.findOne(req.getId());").append(ENTER);
        buffer.append(TAB).append(TAB).append("BeanUtils.copyProperties(req, " + StringUtils.uncapitalize(className) + ");").append(ENTER);
        buffer.append(TAB).append(TAB).append(StringUtils.uncapitalize(className) + "Repository.save(" + StringUtils.uncapitalize(className) + ");").append(ENTER);
        buffer.append(TAB).append("}").append(ENTER);
        buffer.append(ENTER);

        //删除
        buffer.append(TAB).append("public void delete" + className + "(Long id) {").append(ENTER);
        buffer.append(TAB).append(TAB).append("getAndCheck(id);").append(ENTER);
        buffer.append(TAB).append(TAB).append(StringUtils.uncapitalize(className) + "Repository.delete(id);").append(ENTER);
        buffer.append(TAB).append("}").append(ENTER);
        buffer.append(ENTER);

        //详情
        buffer.append(TAB).append("public " + className + "DetailRes detail" + className + "(Long id) {").append(ENTER);
        buffer.append(TAB).append(TAB).append(className + " " + StringUtils.uncapitalize(className) + " = ").append("getAndCheck(id);").append(ENTER);
        buffer.append(TAB).append(TAB).append(className + "DetailRes " + StringUtils.uncapitalize(className) + "DetailRes = new " + className + "DetailRes();").append(ENTER);
        buffer.append(TAB).append(TAB).append("BeanUtils.copyProperties(" + StringUtils.uncapitalize(className) + ", " + StringUtils.uncapitalize(className) + "DetailRes);").append(ENTER);
        buffer.append(TAB).append(TAB).append("return ").append(StringUtils.uncapitalize(className) + "DetailRes;").append(ENTER);
        buffer.append(TAB).append("}").append(ENTER);
        buffer.append(ENTER);

        //分页
        buffer.append(TAB).append("public PageRes<" + className + "PageRes> page" + className + "(" + className + "PageReq req) {").append(ENTER);
        buffer.append(TAB).append(TAB).append("PageRequest pageRequest = new PageRequest(req.getPage(), req.getSize());").append(ENTER);
        buffer.append(TAB).append(TAB).append("Page<" + className + "> page = " + StringUtils.uncapitalize(className) + "Repository.findAll(pageRequest);").append(ENTER);
        buffer.append(TAB).append(TAB).append("PageRes<" + className + "PageRes> response = new PageRes(page);").append(ENTER);
        buffer.append(TAB).append(TAB).append("page.getContent().forEach(" + StringUtils.uncapitalize(className) + " -> {").append(ENTER);
        buffer.append(TAB).append(TAB).append(TAB).append(className + "PageRes " + StringUtils.uncapitalize(className) + "PageRes = new " + className + "PageRes();").append(ENTER);
        buffer.append(TAB).append(TAB).append(TAB).append("BeanUtils.copyProperties(" + StringUtils.uncapitalize(className) + ", " + StringUtils.uncapitalize(className) + "PageRes);").append(ENTER);
        buffer.append(TAB).append(TAB).append(TAB).append("response.getList().add(" + StringUtils.uncapitalize(className) + "PageRes);").append(ENTER);
        buffer.append(TAB).append(TAB).append("});").append(ENTER);
        buffer.append(TAB).append(TAB).append("return response;").append(ENTER);
        buffer.append(TAB).append("}").append(ENTER);
        buffer.append(ENTER);

        //验证数据是否存在
        buffer.append(TAB).append("public " + className + " getAndCheck(Long id) {").append(ENTER);
        buffer.append(TAB).append(TAB).append(className).append(" " + StringUtils.uncapitalize(className) + " = ").append(StringUtils.uncapitalize(className) + "Repository.findOne(id);").append(ENTER);
        buffer.append(TAB).append(TAB).append("if (null == " + StringUtils.uncapitalize(className)).append(") {").append(ENTER);
        buffer.append(TAB).append(TAB).append(TAB).append("throw new BusinessException(Errors.DATA_NOT_EXISTED);").append(ENTER);
        buffer.append(TAB).append(TAB).append("}").append(ENTER);
        buffer.append(TAB).append(TAB).append("return " + StringUtils.uncapitalize(className) + ";").append(ENTER);
        buffer.append(TAB).append("}").append(ENTER);
        buffer.append(ENTER);

        buffer.append("}");
        FileUtil.save(BASE_PATH + SERVICE_PACKAGE.replaceAll("\\.", "/") + "/" + className + "Service.java", buffer.toString());
    }

    /**
     * 生成Controller
     *
     * @param className 实体名
     * @param describe  实体描述
     */
    public static void createControllerClass(String className, String describe) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("package " + CONTROLLER_PACKAGE + ";").append(ENTER);
        buffer.append(ENTER);

        //引入依赖包
        buffer.append("import com.jiuling.controller.response.base.ResponseEntity;").append(ENTER);
        buffer.append("import com.jiuling.controller.response.base.PageRes;").append(ENTER);
        buffer.append("import " + DOMAIN_PACKAGE + "." + className + ";").append(ENTER);
        buffer.append("import " + SERVICE_PACKAGE + "." + className + "Service;").append(ENTER);
        buffer.append("import " + REQ_PACKAGE + "." + className + "AddReq;").append(ENTER);
        buffer.append("import " + REQ_PACKAGE + "." + className + "EditReq;").append(ENTER);
        buffer.append("import " + REQ_PACKAGE + "." + className + "PageReq;").append(ENTER);
        buffer.append("import " + RES_PACKAGE + "." + className + "PageRes;").append(ENTER);
        buffer.append("import " + RES_PACKAGE + "." + className + "DetailRes;").append(ENTER);
        buffer.append("import io.swagger.annotations.Api;").append(ENTER);
        buffer.append("import io.swagger.annotations.ApiOperation;").append(ENTER);
        buffer.append("import org.springframework.web.bind.annotation.*;").append(ENTER);
        buffer.append("import javax.annotation.Resource;").append(ENTER);
        buffer.append("import javax.validation.Valid;").append(ENTER);
        buffer.append(ENTER);

        //填写作者信息
        authorMessage(describe, buffer);

        //引入注解
        buffer.append("@RestController").append(ENTER);
        buffer.append("@RequestMapping(\"/").append(StringUtils.uncapitalize(className)).append("/\")").append(ENTER);
        buffer.append("@Api(tags = \"").append(describe).append("\", produces = \"application/json\"").append(")").append(ENTER);

        buffer.append("public class " + className + "Controller {").append(ENTER);
        buffer.append(ENTER);
        buffer.append(TAB).append("@Resource").append(ENTER);
        buffer.append(TAB).append("private ").append(className).append("Service ").append(StringUtils.uncapitalize(className)).append("Service;").append(ENTER);
        buffer.append(ENTER);

        //生成新增方法
        buffer.append(TAB).append("@ApiOperation(\"" + describe + "-新增\")").append(ENTER);
        buffer.append(TAB).append("@PostMapping(\"" + "add" + className + "\")").append(ENTER);
        buffer.append(TAB).append("public ResponseEntity<Long> add" + className + "(@Valid @RequestBody ").append(className + "AddReq req) {").append(ENTER);
        buffer.append(TAB).append(TAB).append("return new ResponseEntity(" + StringUtils.uncapitalize(className) + "Service.add" + className + "(req));").append(ENTER);
        buffer.append(TAB).append("}").append(ENTER);
        buffer.append(ENTER);

        //生成编辑方法
        buffer.append(TAB).append("@ApiOperation(\"" + describe + "-编辑\")").append(ENTER);
        buffer.append(TAB).append("@PostMapping(\"" + "edit" + className + "\")").append(ENTER);
        buffer.append(TAB).append("public ResponseEntity edit" + className + "(@Valid @RequestBody ").append(className + "EditReq req) {").append(ENTER);
        buffer.append(TAB).append(TAB).append(StringUtils.uncapitalize(className) + "Service.edit" + className + "(req);").append(ENTER);
        buffer.append(TAB).append(TAB).append("return new ResponseEntity();").append(ENTER);
        buffer.append(TAB).append("}").append(ENTER);
        buffer.append(ENTER);

        //生成删除方法
        buffer.append(TAB).append("@ApiOperation(\"" + describe + "-删除\")").append(ENTER);
        buffer.append(TAB).append("@GetMapping(\"" + "delete" + className + "/{id}\")").append(ENTER);
        buffer.append(TAB).append("public ResponseEntity delete" + className + "(@PathVariable(\"id\") Long id) {").append(ENTER);
        buffer.append(TAB).append(TAB).append(StringUtils.uncapitalize(className) + "Service.delete" + className + "(id);").append(ENTER);
        buffer.append(TAB).append(TAB).append("return new ResponseEntity();").append(ENTER);
        buffer.append(TAB).append("}").append(ENTER);
        buffer.append(ENTER);

        //生成详情方法
        buffer.append(TAB).append("@ApiOperation(\"" + describe + "-详情\")").append(ENTER);
        buffer.append(TAB).append("@GetMapping(\"" + "detail" + className + "/{id}\")").append(ENTER);
        buffer.append(TAB).append("public ResponseEntity<" + className + "DetailRes> detail" + className + "(@PathVariable(\"id\") Long id) {").append(ENTER);
        buffer.append(TAB).append(TAB).append("return new ResponseEntity(").append(StringUtils.uncapitalize(className) + "Service.detail" + className + "(id));").append(ENTER);
        buffer.append(TAB).append("}").append(ENTER);
        buffer.append(ENTER);

        //生成分页方法
        buffer.append(TAB).append("@ApiOperation(\"" + describe + "-分页\")").append(ENTER);
        buffer.append(TAB).append("@PostMapping(\"" + "page" + className + "\")").append(ENTER);
        buffer.append(TAB).append("public ResponseEntity<PageRes<" + className + "PageRes>> page" + className + "(@Valid @RequestBody ").append(className + "PageReq req) {").append(ENTER);
        buffer.append(TAB).append(TAB).append("return new ResponseEntity(" + StringUtils.uncapitalize(className) + "Service.page" + className + "(req));").append(ENTER);
        buffer.append(TAB).append("}").append(ENTER);
        buffer.append(ENTER);

        buffer.append("}");
        FileUtil.save(BASE_PATH + CONTROLLER_PACKAGE.replaceAll("\\.", "/") + "/" + className + "Controller.java", buffer.toString());
    }

    /**
     * 生成新增请求类
     *
     * @param clazz     类对象
     * @param className 实体名
     * @param describe  实体描述
     */
    public static void createAddReqClass(Class clazz, String className, String describe) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("package " + REQ_PACKAGE + ";").append(ENTER);
        buffer.append(ENTER);

        //引入依赖包
        buffer.append("import io.swagger.annotations.ApiModel;").append(ENTER);
        buffer.append("import io.swagger.annotations.ApiModelProperty;").append(ENTER);
        buffer.append("import lombok.Data;").append(ENTER);
        buffer.append(ENTER);

        //填写作者信息
        authorMessage(describe, buffer);

        buffer.append("@Data").append(ENTER);
        buffer.append("@ApiModel(\"").append(describe).append("-新增\")").append(ENTER);
        buffer.append("public class " + className + "AddReq {").append(ENTER);
        buffer.append(ENTER);

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Type genericType = field.getGenericType();

            //字段描述
            Desc desc = field.getAnnotation(Desc.class);
            if (null == desc) {
                buffer.append(TAB).append("@ApiModelProperty(\"").append("\")").append(ENTER);
            } else {
                if (desc.isNeed() && getType(genericType).equals("String")) {
                    buffer.append(TAB).append("@NotBlank(message = \"").append(desc.value()).append("不能为空\")").append(ENTER);
                }
                if (desc.isNeed() && (!getType(genericType).equals("String"))) {
                    buffer.append(TAB).append("@NotNull(message =\"").append(desc.value()).append("不能为空\")").append(ENTER);
                }
                buffer.append(TAB).append("@ApiModelProperty(\"").append(desc.value()).append("\")").append(ENTER);
            }

            buffer.append(TAB).append("private ").append(getType(genericType)).append(" ").append(field.getName()).append(";").append(ENTER);
            buffer.append(ENTER);
        }

        buffer.append("}");
        FileUtil.save(BASE_PATH + REQ_PACKAGE.replaceAll("\\.", "/") + "/" + className + "AddReq.java", buffer.toString());
    }

    /**
     * 生成编辑请求类
     *
     * @param clazz     类对象
     * @param className 实体名
     * @param describe  实体描述
     */
    public static void createEditReqClass(Class clazz, String className, String describe) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("package " + REQ_PACKAGE + ";").append(ENTER);
        buffer.append(ENTER);

        //引入依赖包
        buffer.append("import io.swagger.annotations.ApiModel;").append(ENTER);
        buffer.append("import io.swagger.annotations.ApiModelProperty;").append(ENTER);
        buffer.append("import lombok.Data;").append(ENTER);
        buffer.append(ENTER);

        //填写作者信息
        authorMessage(describe, buffer);

        buffer.append("@Data").append(ENTER);
        buffer.append("@ApiModel(\"").append(describe).append("-编辑\")").append(ENTER);
        buffer.append("public class " + className + "EditReq {").append(ENTER);
        buffer.append(ENTER);

        buffer.append(TAB).append("@ApiModelProperty(\"id\")").append(ENTER);
        buffer.append(TAB).append("private Long id;").append(ENTER);
        buffer.append(ENTER);

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Type genericType = field.getGenericType();
            Desc desc = field.getAnnotation(Desc.class);
            if (null == desc) {
                buffer.append(TAB).append("@ApiModelProperty(\"").append("\")").append(ENTER);
            } else {
                if (desc.isNeed() && getType(genericType).equals("String")) {
                    buffer.append(TAB).append("@NotBlank(message = \"").append(desc.value()).append("不能为空\")").append(ENTER);
                }
                if (desc.isNeed() && (!getType(genericType).equals("String"))) {
                    buffer.append(TAB).append("@NotNull(message =\"").append(desc.value()).append("不能为空\")").append(ENTER);
                }
                buffer.append(TAB).append("@ApiModelProperty(\"").append(desc.value()).append("\")").append(ENTER);
            }
            buffer.append(TAB).append("private ").append(getType(genericType)).append(" ").append(field.getName()).append(";").append(ENTER);
            buffer.append(ENTER);
        }

        buffer.append("}");
        FileUtil.save(BASE_PATH + REQ_PACKAGE.replaceAll("\\.", "/") + "/" + className + "EditReq.java", buffer.toString());
    }

    /**
     * 生成分页请求类
     *
     * @param className 实体名
     * @param describe  实体描述
     */
    public static void createPageReqClass(String className, String describe) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("package " + REQ_PACKAGE + ";").append(ENTER);
        buffer.append(ENTER);

        //引入依赖包
        buffer.append("import io.swagger.annotations.ApiModel;").append(ENTER);
        buffer.append("import io.swagger.annotations.ApiModelProperty;").append(ENTER);
        buffer.append("import com.controller.req.base.PageReq;").append(ENTER);
        buffer.append("import lombok.Data;").append(ENTER);
        buffer.append(ENTER);

        //填写作者信息
        authorMessage(describe, buffer);

        buffer.append("@Data").append(ENTER);
        buffer.append("@ApiModel(\"").append(describe).append("-分页请求\")").append(ENTER);

        buffer.append("public class " + className + "PageReq extends PageReq {").append(ENTER);
        buffer.append(ENTER);

        buffer.append("}");
        FileUtil.save(BASE_PATH + REQ_PACKAGE.replaceAll("\\.", "/") + "/" + className + "PageReq.java", buffer.toString());
    }

    /**
     * 生成分页返回类
     *
     * @param clazz     类对象
     * @param className 实体名
     * @param describe  实体描述
     */
    public static void createPageResClass(Class clazz, String className, String describe) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("package " + RES_PACKAGE + ";").append(ENTER);
        buffer.append(ENTER);

        //引入依赖包
        buffer.append("import io.swagger.annotations.ApiModel;").append(ENTER);
        buffer.append("import io.swagger.annotations.ApiModelProperty;").append(ENTER);
        buffer.append("import lombok.Data;").append(ENTER);
        buffer.append(ENTER);

        //填写作者信息
        authorMessage(describe, buffer);

        buffer.append("@Data").append(ENTER);
        buffer.append("@ApiModel(\"").append(describe).append("-分页返回\")").append(ENTER);

        buffer.append("public class " + className + "PageRes {").append(ENTER);
        buffer.append(ENTER);

        buffer.append(TAB).append("@ApiModelProperty(\"id\")").append(ENTER);
        buffer.append(TAB).append("private Long id;").append(ENTER);
        buffer.append(ENTER);

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Type genericType = field.getGenericType();
            Desc desc = field.getAnnotation(Desc.class);
            if (null == desc) {
                buffer.append(TAB).append("@ApiModelProperty(\"").append("\")").append(ENTER);
            } else {
                buffer.append(TAB).append("@ApiModelProperty(\"").append(desc.value()).append("\")").append(ENTER);
            }
            buffer.append(TAB).append("private ").append(getType(genericType)).append(" ").append(field.getName()).append(";").append(ENTER);
            buffer.append(ENTER);
        }

        buffer.append("}");
        FileUtil.save(BASE_PATH + RES_PACKAGE.replaceAll("\\.", "/") + "/" + className + "PageRes.java", buffer.toString());
    }

    /**
     * 生成详情返回类
     *
     * @param clazz     类对象
     * @param className 实体名
     * @param describe  实体描述
     */
    public static void createDetailResClass(Class clazz, String className, String describe) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("package " + RES_PACKAGE + ";").append(ENTER);
        buffer.append(ENTER);

        buffer.append("import io.swagger.annotations.ApiModel;").append(ENTER);
        buffer.append("import io.swagger.annotations.ApiModelProperty;").append(ENTER);
        buffer.append("import lombok.Data;").append(ENTER);
        buffer.append(ENTER);

        //填写作者信息
        authorMessage(describe, buffer);

        buffer.append("@Data").append(ENTER);
        buffer.append("@ApiModel(\"").append(describe).append("-详情\")").append(ENTER);

        buffer.append("public class " + className + "DetailRes {").append(ENTER);
        buffer.append(ENTER);

        buffer.append(TAB).append("@ApiModelProperty(\"id\")").append(ENTER);
        buffer.append(TAB).append("private Long id;").append(ENTER);
        buffer.append(ENTER);

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Type genericType = field.getGenericType();
            Desc desc = field.getAnnotation(Desc.class);
            if (null == desc) {
                buffer.append(TAB).append("@ApiModelProperty(\"").append("\")").append(ENTER);
            } else {
                buffer.append(TAB).append("@ApiModelProperty(\"").append(desc.value()).append("\")").append(ENTER);
            }
            buffer.append(TAB).append("private ").append(getType(genericType)).append(" ").append(field.getName()).append(";").append(ENTER);
            buffer.append(ENTER);
        }
        buffer.append("}");
        FileUtil.save(BASE_PATH + RES_PACKAGE.replaceAll("\\.", "/") + "/" + className + "DetailRes.java", buffer.toString());
    }


    private static void authorMessage(String describe, StringBuilder buffer) {
        buffer.append("/** ").append(ENTER);
        buffer.append(" * @Author " + AUTHOR).append(ENTER);
        buffer.append(" * @Describe " + describe).append(ENTER);
        buffer.append(" * @Date " + NOW_DATE).append(ENTER);
        buffer.append(" */").append(ENTER);
    }

    public static String getType(Type type) {
        String typeName = type.getTypeName();
        String[] split = typeName.split("\\.");
        return split[split.length - 1];
    }

}

