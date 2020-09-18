package com.fleet.oshi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.OperatingSystem;

@RestController
public class InfoController {

    /**
     * 内存信息
     */
    @RequestMapping("/memory")
    public GlobalMemory memory() {
        // 系统信息
        SystemInfo si = new SystemInfo();
        // 硬件信息
        HardwareAbstractionLayer hal = si.getHardware();
        return hal.getMemory();
    }

    /**
     * CPU信息
     */
    @RequestMapping("/cpu")
    public CentralProcessor cpu() {
        // 系统信息
        SystemInfo si = new SystemInfo();
        // 硬件信息
        HardwareAbstractionLayer hal = si.getHardware();
        return hal.getProcessor();
    }

    /**
     * 计算机系统信息
     */
    @RequestMapping("/cs")
    public ComputerSystem cs() {
        // 系统信息
        SystemInfo si = new SystemInfo();
        // 硬件信息
        HardwareAbstractionLayer hal = si.getHardware();
        return hal.getComputerSystem();
    }

    /**
     * 操作系统信息
     */
    @RequestMapping("/os")
    public OperatingSystem os() {
        // 系统信息
        SystemInfo si = new SystemInfo();
        // 操作系统信息
        return si.getOperatingSystem();
    }

    /**
     * 传感器
     */
    @RequestMapping("/sensors")
    public Sensors sensors() {
        // 系统信息
        SystemInfo si = new SystemInfo();
        // 硬件信息
        HardwareAbstractionLayer hal = si.getHardware();
        return hal.getSensors();
    }
}
