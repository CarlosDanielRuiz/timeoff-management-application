# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
Vagrant.configure("2") do |config|
  config.vm.define "debian10" do |debian10_config|
    debian10_config.vm.box = "generic/debian10"
    debian10_config.vm.network "private_network", ip: "192.168.100.2"
    debian10_config.vm.network "forwarded_port", guest: 3000, host: 3000
    debian10_config.vm.provider "vmware_fusion" do |vmware_fusion|
      vmware_fusion.memory = "2048" # 2 GBs
    end
  end
end
