package org.nagarro.DAO;

import java.util.List;

import org.nagarro.model.ImageFile;
import org.nagarro.model.User;

public interface ImagesDAO {

	void saveUser(User user);

	User getUserByEmail(String email);

	void saveImageFile(ImageFile image);

	List<ImageFile> getImages(User user);

	void deleteImg(int imageId);

	ImageFile getImageFileById(int currentImageId);

	long getUsedCap(User user);

}