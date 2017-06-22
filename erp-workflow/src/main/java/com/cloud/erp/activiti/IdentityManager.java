package com.cloud.erp.activiti;


import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.activiti.exception.FoundMoreGroupOrRoleException;
import com.cloud.erp.activiti.exception.FoundMoreUserException;
import com.cloud.erp.entities.table.Role;
import com.cloud.erp.entities.table.User;

/**
 * 
 * @author Bollen
 *
 */
@Service("identityManager")
public class IdentityManager {

	private static final Logger log = LoggerFactory.getLogger(IdentityManager.class);
	
	private static final String EMPTY = "";
	
	@Autowired
	private IdentityService identityService;
	
	public void saveActivitiUser(User user){
		String userId = user.getName();
		List<org.activiti.engine.identity.User> users = identityService.createUserQuery().userId(userId).list();
		if(users.size() == 1){
			updateActivitiUser(user, users.get(0));
		}else if(users.size() > 1){
			if(log.isErrorEnabled()){
				log.error("found more users from activiti user");
			}
			throw new FoundMoreUserException();
		}else {
			newActivitiUser(user);
		}
	}
	
	public void saveActivitiGroup(Role role){
		String groupId = role.getName();
		List<Group> groups = identityService.createGroupQuery().groupId(groupId).list();
		if(groups.size() == 1){
			updateActivitiGroup(role, groups.get(0));
		}else if(groups.size() > 1){
			if(log.isErrorEnabled()){
				log.error("found more group from activiti group");
			}
			throw new FoundMoreGroupOrRoleException();
		}else {
			newActivitiGroup(role);
		}
	}
		
	public void addMembershipToActivitiGroups(String userId, List<String> groupIds){
		deleteMembershipFromActivitiGroups(userId);
		for(String groupId : groupIds){
			if(log.isDebugEnabled()){
				log.debug("add user id: {} to group id:{} ", userId, groupId);
			}
			identityService.createMembership(userId, groupId);
		}
	}
	
	public void delUser(String userId){
		identityService.deleteUser(userId);
		deleteMembershipFromActivitiGroups(userId);
	}
	
	
	public void delGroup(String groupId){
		identityService.deleteGroup(groupId);
	}
	
	private void newActivitiUser(User user){
		String userId = user.getName();
		org.activiti.engine.identity.User newUser = identityService.newUser(userId);
		cloneAndSaveActivitUser(user, newUser);
		if(log.isDebugEnabled()){
			log.debug("add activiti user: {}", userId);
		}
	}
	
	private void updateActivitiUser(User user, org.activiti.engine.identity.User activitiUser){
		cloneAndSaveActivitUser(user, activitiUser);
	} 
	
	private void cloneAndSaveActivitUser(User user, org.activiti.engine.identity.User activitiUser){
		activitiUser.setFirstName(user.getName());
		activitiUser.setLastName(EMPTY);
		activitiUser.setPassword(EMPTY);
		activitiUser.setEmail(user.getEmail());
		identityService.saveUser(activitiUser);
	}
	
	private void newActivitiGroup(Role role){
		String groupId = role.getName();
		Group activitiGroup = identityService.newGroup(groupId);
		cloneAndSaveActivitiGroup(role, activitiGroup);
		if(log.isDebugEnabled()){
			log.debug("add activiti group: {}", groupId);
		}
	}
	
	private void updateActivitiGroup(Role role, Group activitiGroup){
		cloneAndSaveActivitiGroup(role, activitiGroup);
	}
	
	private void cloneAndSaveActivitiGroup(Role role, Group activitiGroup){
		activitiGroup.setName(role.getName());
		activitiGroup.setType(EMPTY);
		identityService.saveGroup(activitiGroup);
	}
	
	private void deleteMembershipFromActivitiGroups(String userId){
		List<Group> groups = identityService.createGroupQuery().groupMember(userId).list();
		for(Group group : groups){
			identityService.deleteMembership(userId, group.getId());
		}
	}
	
}
