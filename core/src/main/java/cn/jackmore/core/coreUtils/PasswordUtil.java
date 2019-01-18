/*
 * Project Name: dctp
 * File Name: PasswordUtil.java
 * Class Name: PasswordUtil
 *
 * Copyright 2014 Hengtian Software Inc
 *
 * Licensed under the Hengtiansoft
 *
 * http://www.hengtiansoft.com
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.jackmore.core.coreUtils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class PasswordUtil {
    private static final String DEFAULT_SALT = "2018jackmore2018jackmore2018";

    /**
     * 获取十六进制字符串形式的MD5摘要
     */
    private static String md5Hex(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(src.getBytes());
            return new BigInteger(1, bs).toString(16);
        } catch (Exception e) {
            return null;
        }
    }

    public static String MD5DefaultSalt(String password) {
        return md5Hex(md5Hex(password) + DEFAULT_SALT);
    }

    public static String MD5Salt(String password, String salt) {
        return md5Hex(md5Hex(password) + salt);
    }

}
