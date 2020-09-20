package com.fleet.oshi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.FileSystem;
import oshi.software.os.NetworkParams;
import oshi.software.os.OperatingSystem;

import java.util.List;

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
     * 传感器信息
     */
    @RequestMapping("/sensors")
    public Sensors sensors() {
        // 系统信息
        SystemInfo si = new SystemInfo();
        // 硬件信息
        HardwareAbstractionLayer hal = si.getHardware();
        return hal.getSensors();
    }

    /**
     * 电源信息
     */
    @RequestMapping("/powerSources")
    public List<PowerSource> powerSources() {
        // 系统信息
        SystemInfo si = new SystemInfo();
        // 硬件信息
        HardwareAbstractionLayer hal = si.getHardware();
        return hal.getPowerSources();
    }

    /**
     * 磁盘信息
     */
    @RequestMapping("/diskStores")
    public List<HWDiskStore> diskStores() {
        // 系统信息
        SystemInfo si = new SystemInfo();
        // 硬件信息
        HardwareAbstractionLayer hal = si.getHardware();
        return hal.getDiskStores();
    }

    /**
     * 文件系统向欧盟西欧
     */
    @RequestMapping("/fs")
    public FileSystem fs() {
        // 系统信息
        SystemInfo si = new SystemInfo();
        // 操作系统信息
        OperatingSystem os = si.getOperatingSystem();
        return os.getFileSystem();
    }

    /**
     * 网络接口信息
     */
    @RequestMapping("/networkIFs")
    public List<NetworkIF> networkIFs() {
        // 系统信息
        SystemInfo si = new SystemInfo();
        // 硬件信息
        HardwareAbstractionLayer hal = si.getHardware();
        return hal.getNetworkIFs();
    }

    /**
     * 网络参数信息
     */
    @RequestMapping("/networkParams")
    public NetworkParams networkParams() {
        // 系统信息
        SystemInfo si = new SystemInfo();
        // 操作系统信息
        OperatingSystem os = si.getOperatingSystem();
        return os.getNetworkParams();
    }

    /**
     * 显示器信息
     */
    @RequestMapping("/displays")
    public List<Display> displays() {
        // 系统信息
        SystemInfo si = new SystemInfo();
        // 硬件信息
        HardwareAbstractionLayer hal = si.getHardware();
        return hal.getDisplays();
    }

    /**
     * USB设备信息
     */
    @RequestMapping("/usbDevices")
    public List<UsbDevice> usbDevices() {
        // 系统信息
        SystemInfo si = new SystemInfo();
        // 硬件信息
        HardwareAbstractionLayer hal = si.getHardware();
        return hal.getUsbDevices(true);
    }
}
