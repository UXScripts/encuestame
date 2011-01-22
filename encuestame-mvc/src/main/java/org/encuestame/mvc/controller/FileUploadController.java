/*
 ************************************************************************************
 * Copyright (C) 2001-2011 encuestame: system online surveys Copyright (C) 2009
 * encuestame Development Team.
 * Licensed under the Apache Software License version 2.0
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to  in writing,  software  distributed
 * under the License is distributed  on  an  "AS IS"  BASIS,  WITHOUT  WARRANTIES  OR
 * CONDITIONS OF ANY KIND, either  express  or  implied.  See  the  License  for  the
 * specific language governing permissions and limitations under the License.
 ************************************************************************************
 */
package org.encuestame.mvc.controller;

import java.io.File;
import java.io.IOException;

import org.encuestame.core.util.MD5Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

/**
 * Upload File Controller.
 * @author Picado, Juan juanATencuestame.org
 * @since Jan 16, 2011 11:47:33 AM
 * @version $Id:$
 */
@Controller
public class FileUploadController extends BaseController {

    /**
     * Upload Profile for User Account.
     * @param multipartFile
     * @return
     */
    @RequestMapping(value = "/user/profile/file/upload", method = RequestMethod.POST)
    public ModelAndView handleUserProfileFileUpload(
            @RequestParam("file") MultipartFile multipartFile) {
        ModelAndView mav = new ModelAndView(new MappingJacksonJsonView());
        if (!multipartFile.isEmpty()) {
            log.debug(multipartFile.getName());
            String orgName = multipartFile.getOriginalFilename();
            log.debug("org name "+orgName);
            //TODO: convert name to numbers, MD5 hash.
            final String filePath = MD5Utils.shortMD5(getPictureService().getAccountUserPicturePath("FAKE") + orgName);
            try {
                //TODO: replace FAKE by getUserAuthenticationUsername
                log.debug("org filePath "+filePath);
                final File dest = new File(filePath);
                log.debug("dest  "+dest);
                multipartFile.transferTo(dest);
                log.debug("transferTo after");
                //TODO: after save image, we need relationship user with profile picture.
                //I suggest store ID on user account table, to retrieve easily future profile image.
                //BUG 102
            } catch (IllegalStateException e) {
                e.printStackTrace();
                log.error("File uploaded failed:" + orgName);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("File uploaded failed:" + orgName);
            }
            // Save the file here
            mav.addObject("status", "saved");
            mav.addObject("id", filePath);
        } else {
            mav.addObject("status", "failed");
        }
        return mav;
    }

    /**  TODO: we can add more methods to upload different types of files. **/
}