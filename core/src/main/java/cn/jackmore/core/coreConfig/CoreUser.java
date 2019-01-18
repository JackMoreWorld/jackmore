/**
 * FileName: CoreUser
 * Author:   JackMore
 * Date:     2019/1/18 10:47
 * Description:
 */
package cn.jackmore.core.coreConfig;

import lombok.Data;

/**
 * @author JackMore
 * @create 2019/1/18
 * @since 1.0.0
 */
@Data
public class CoreUser {
    private String googleAuth;
    private Long googleCode;
    private boolean shouldValid;
}