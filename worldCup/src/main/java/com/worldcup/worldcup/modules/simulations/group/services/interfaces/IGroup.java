package com.worldcup.worldcup.modules.simulations.group.services.interfaces;

import com.worldcup.worldcup.modules.simulations.group.dto.GroupDTO;
import com.worldcup.worldcup.modules.simulations.group.entity.Group;

import java.util.List;

public interface IGroup {
    public String CreateCountry(GroupDTO groupDTO);
    public List<Group> GetAll();
    public Group GetById(Integer groupId);
    public Group Update(Integer groupId, GroupDTO groupDTO);
    public Group PartialUpdate(Integer groupId, GroupDTO groupDTO);
    public boolean Delete(Integer groupId);
    public boolean LogicalDelete(Integer groupId);

    String Create(GroupDTO groupDTO);
}
