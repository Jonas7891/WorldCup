package com.worldcup.modules.simulations.groups.services.interfaces;

import com.worldcup.modules.simulations.groups.dto.GroupsDTO;
import com.worldcup.modules.simulations.groups.entity.Groups;

import java.util.List;

public interface IGroups {
    public String CreateCountry(GroupsDTO groupsDTO);
    public List<Groups> GetAll();
    public Groups GetById(Integer groupId);
    public Groups Update(Integer groupId, GroupsDTO groupsDTO);
    public Groups PartialUpdate(Integer groupId, GroupsDTO groupsDTO);
    public boolean Delete(Integer groupId);
    public boolean LogicalDelete(Integer groupId);

    String Create(GroupsDTO groupsDTO);
}
