package yass.jouao.labx.serviceImpl;

import java.lang.reflect.Field;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yass.jouao.labx.DTOs.UserLabDTO;
import yass.jouao.labx.entities.UserLab;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IUserLabRepository;
import yass.jouao.labx.serviceImpl.Mappers.UserMapper;
import yass.jouao.labx.services.IUserLabService;

@Service
public class UserLabServiceImpl implements IUserLabService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private IUserLabRepository userLabRepository;

	@Override
	@Transactional
	public UserLabDTO getUserLabByIdService(Long id) throws NotFoundException {
		Optional<UserLab> optionalUserLab = userLabRepository.findById(id);
		if (optionalUserLab.isPresent()) {
			UserLabDTO userLabDTO = userMapper.fromUserToUserDTO(optionalUserLab.get());
			return userLabDTO;
		} else {
			throw new NotFoundException("User not found");
		}
	}

	@Override
	@Transactional
	public UserLabDTO updateUserLabService(Long userId, UserLabDTO u) throws NotFoundException, IllegalAccessException {
		UserLab userToUpdate = userMapper.fromUserDTOToUser(getUserLabByIdService(userId));
		u.setId(userId);
		UserLab userNewData = userMapper.fromUserDTOToUser(u);
		updateNonNullFields(userToUpdate, userNewData);
		UserLabDTO userLabDTO = userMapper.fromUserToUserDTO(userLabRepository.save(userToUpdate));
		return userLabDTO;
	}

	@Override
	@Transactional
	public UserLabDTO addUserLabService(UserLabDTO u) {
		UserLab userLab = userMapper.fromUserDTOToUser(u);
		UserLabDTO userLabDTO = userMapper.fromUserToUserDTO(userLabRepository.save(userLab));
		return userLabDTO;
	}

	private void updateNonNullFields(UserLab existingEntity, UserLab updatedEntity) throws IllegalAccessException {
		Field[] fields = UserLab.class.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Object updatedValue = field.get(updatedEntity);
			if (updatedValue != null) {
				field.set(existingEntity, updatedValue);
			}
		}
	}

}
