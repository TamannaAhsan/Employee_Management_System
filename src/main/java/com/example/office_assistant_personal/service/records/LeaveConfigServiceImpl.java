package com.example.office_assistant_personal.service.records;

import com.example.office_assistant_personal.DTO.LeaveTypeDTO;
import com.example.office_assistant_personal.DTO.YearlyLeaveDTO;
import com.example.office_assistant_personal.DTO.mapper.AutoUserMapper;
import com.example.office_assistant_personal.entity.records.LeaveType;
import com.example.office_assistant_personal.entity.records.YearlyLeave;
import com.example.office_assistant_personal.exception.ApiNotAcceptableException;
import com.example.office_assistant_personal.exception.ApiSystemException;
import com.example.office_assistant_personal.repository.records.LeaveTypeRepository;
import com.example.office_assistant_personal.repository.records.YearlyLeaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.office_assistant_personal.utils.Utility.isNotNull;

@Service
@RequiredArgsConstructor
public class LeaveConfigServiceImpl implements LeaveConfigService {

    private final LeaveTypeRepository leaveTypeRepository;

    private final YearlyLeaveRepository yearlyLeaveRepository;

    @Override
    public LeaveTypeDTO create(LeaveTypeDTO leaveTypeDTO) throws ApiSystemException {
        try {
            if (isNotNull(leaveTypeRepository.findById(leaveTypeDTO.getId()))) {
                throw new ApiNotAcceptableException("leave.type.id.already.exist");
            }
            LeaveType leaveType = AutoUserMapper.MAPPER.mapToLeaveType(leaveTypeDTO);
            LeaveType savedLeaveType = leaveTypeRepository.save(leaveType);
            LeaveTypeDTO savedLeaveTypeDTO = AutoUserMapper.MAPPER.mapToLeaveTypeDTO(savedLeaveType);
            return savedLeaveTypeDTO;
        }catch (Exception e) {
            throw new ApiSystemException("leave.type.not.created");
        }
    }

    @Override
    public List<LeaveTypeDTO> get() {
       List<LeaveType> leaveType = leaveTypeRepository.findAll();
       return leaveType.stream()
               .map(element-> AutoUserMapper.MAPPER.mapToLeaveTypeDTO(element))
               .collect(Collectors.toList());
    }

    @Override
    public LeaveTypeDTO getById(Long id) throws ApiSystemException {
        try {
            LeaveType leaveType = leaveTypeRepository.findById(id)
                    .orElseThrow(()-> new ApiSystemException("Leave Type Not Found"));
            LeaveTypeDTO leaveTypeDTO = AutoUserMapper.MAPPER.mapToLeaveTypeDTO(leaveType);
            return leaveTypeDTO;

        }catch (Exception e) {
            throw new ApiSystemException("leave.type.not.found");
        }
    }

    @Override
    public void deleteLeaveType(Long id) throws ApiSystemException {
        try {
            leaveTypeRepository.deleteById(id);
        }catch (Exception e) {
            throw new ApiSystemException("leave.type.not.found");
        }
    }

    @Override
    public LeaveTypeDTO update(LeaveTypeDTO leaveTypeDTO) throws ApiSystemException {
        try {
            LeaveType leaveType = leaveTypeRepository.findById(leaveTypeDTO.getId())
                    .orElseThrow(()-> new ApiSystemException("Leave Type not found"));
            leaveType.setName(leaveTypeDTO.getName());
            leaveType.setLabel(leaveTypeDTO.getLabel());
            LeaveType updatedLeaveType = leaveTypeRepository.save(leaveType);
            LeaveTypeDTO updatedLeaveTypeDTO = AutoUserMapper.MAPPER.mapToLeaveTypeDTO(updatedLeaveType);
            return updatedLeaveTypeDTO;

        }catch (Exception e) {
            throw new ApiSystemException("leave.type.not.found");
        }
    }

    @Override
    public YearlyLeaveDTO createYearlyLeave(YearlyLeaveDTO yearlyLeaveDTO, Long id) throws ApiSystemException {
        try {
            if (isNotNull(yearlyLeaveRepository.findById(yearlyLeaveDTO.getId()))) {
                throw new ApiNotAcceptableException("leave.type.id.already.exist");
            }
            YearlyLeave yearlyLeave = AutoUserMapper.MAPPER.mapToYearlyLeave(yearlyLeaveDTO);
            LeaveType leaveType = leaveTypeRepository.findById(id)
                    .orElseThrow(()-> new ApiSystemException("Leave Type not found"));
            yearlyLeave.setLeaveType(leaveType);
            YearlyLeave savedYearlyLeave = yearlyLeaveRepository.save(yearlyLeave);
            YearlyLeaveDTO savedYearlyLeaveDTO = AutoUserMapper.MAPPER.mapToYearlyLeaveDTO(savedYearlyLeave);
            return savedYearlyLeaveDTO;

        }catch (Exception e) {
            throw new ApiSystemException("yearly.leave.not.created");
        }
    }

    @Override
    public List<YearlyLeaveDTO> getAllLeave() {
        List<YearlyLeave> yearlyLeave = yearlyLeaveRepository.findAll();
        return yearlyLeave.stream()
                .map(element-> AutoUserMapper.MAPPER.mapToYearlyLeaveDTO(element))
                .collect(Collectors.toList());

    }

    @Override
    public YearlyLeaveDTO getByYearlyLeaveId(Long id) throws ApiSystemException {
        try {
            YearlyLeave yearlyLeave = yearlyLeaveRepository.findById(id)
                    .orElseThrow(()-> new ApiSystemException("Yearly Leave not found"));
            YearlyLeaveDTO yearlyLeaveDTO = AutoUserMapper.MAPPER.mapToYearlyLeaveDTO(yearlyLeave);
            return yearlyLeaveDTO;

        }catch (Exception e) {
            throw new ApiSystemException("yearly.leave.not.found");
        }

    }

    @Override
    public void deleteYearlyLeave(Long id) throws ApiSystemException {
        try {
            yearlyLeaveRepository.deleteById(id);
        }catch (Exception e) {
            throw new ApiSystemException("yearly.leave.not.found");
        }
    }

    @Override
    public YearlyLeaveDTO updateYearlyLeave(YearlyLeaveDTO yearlyLeaveDTO) throws ApiSystemException {
        try {
            YearlyLeave yearlyLeave = yearlyLeaveRepository.findById(yearlyLeaveDTO.getId())
                    .orElseThrow(()-> new ApiSystemException("Yearly Leave not found"));
            LeaveType leaveType = leaveTypeRepository.findById(yearlyLeaveDTO.getLeaveTypeId().longValue())
                    .orElseThrow(()-> new RuntimeException("Leave Type not found"));
            yearlyLeave.setYear(yearlyLeaveDTO.getYear());
            yearlyLeave.setMaximumDay(yearlyLeaveDTO.getMaximumDay());
            yearlyLeave.setMinimumDay(yearlyLeaveDTO.getMinimumDay());
            yearlyLeave.setLeaveType(leaveType);
            YearlyLeave updatedYearlyLeave = yearlyLeaveRepository.save(yearlyLeave);
            YearlyLeaveDTO updatedYearlyLeaveDTO = AutoUserMapper.MAPPER.mapToYearlyLeaveDTO(updatedYearlyLeave);
            return updatedYearlyLeaveDTO;

        }catch (Exception e) {
            throw new ApiSystemException("yearly.leave.not.found");
        }

    }
}
