# -*- coding: utf-8 -*-

import mock
import time
import unittest

from sensetile import imager

class ImagerTestCase(unittest.TestCase):
    """
    """
    
    def setUp(self):
        self.image_server_name = "image_server"
        self.target_name = "target.ucd.ie"
        self.setUp_mock_executor()
        self.img = imager.Imager(self.image_server_name, executor_class = self.mock_executor_class)
    
    def setUp_mock_executor(self):
        fake_return = (0, "", "")
        self.mock_executor = mock.Mock( spec = ["ssh_run"] )
        self.mock_executor_class = mock.Mock()
        self.mock_executor_class.return_value = self.mock_executor
    
    def tearDown(self):
        self.img = None
        self.tearDown_mock_executor()
        self.target_name = None
        self.image_server_name = None
    
    def tearDown_mock_executor(self):
        self.mock_executor_class = None
        self.mock_executor = None
    
    def test__create(self):
        """
        image creation
        """
        
        img = imager.Imager(self.image_server_name)
        self.failIfEqual(None, img)
    
    def test__image_incremental_update(self):
        """
        incremental image target
        """
        
        self.img._image_incremental_update(self.target_name)
        self.assertEqual( 
            ((["si_updateclient", "--server " + imager._remove_domain(self.image_server_name), "--yes"],), {}),
            self.mock_executor_class.call_args_list[0])
        self.assertEqual(
            ("ssh_run", (self.target_name, "root"), {"check": True}), 
            self.mock_executor.method_calls[0])
    
    def test__image_complete_update(self):
        """
        complete image target
        """
        
        flavor = "ST64"
        ip_address = "127.0.0.1"
        self.img._image_complete_update(self.target_name, flavor, image_server_ip_address = ip_address)
        self.assertEqual( 
            ((["si_updateclient", 
               "--server " + ip_address, 
               "--yes", 
               "--autoinstall", 
               "--flavor " + flavor],), 
             {}),
            self.mock_executor_class.call_args_list[0])
        self.assertEqual(
            ("ssh_run", (self.target_name, "root"), {"check": True}), 
            self.mock_executor.method_calls[0])
    
    def test__push_overrides(self):
        """
        push overrides on target
        """
        
        self.img._push_overrides(self.target_name)
        self.assertEqual( 
            ((["si_pushoverrides", imager._remove_domain(self.target_name)],), {}),
            self.mock_executor_class.call_args_list[0])
        self.assertEqual(
            ("ssh_run", (self.image_server_name, "root"), {"check": True}), 
            self.mock_executor.method_calls[0])
    
    def test__update_grub_menu(self):
        """
        test upgrade grub menu
        """
        
        self.img._update_grub_menu(self.target_name)
        self.assertEqual(1, len(self.mock_executor_class.call_args_list))
        self.assertEqual(
            ("ssh_run", (self.target_name, "root"), {"check": True}), 
            self.mock_executor.method_calls[0])
    
    def test__reboot(self):
        """
        test reboot
        """
        
        self.img._reboot(self.target_name)
        self.assertEqual(1, len(self.mock_executor_class.call_args_list))
        self.assertEqual(
            ("ssh_run", (self.target_name, "root"), {"check": True}), 
            self.mock_executor.method_calls[0])
    
    def test_image_incremental(self):
        """
        test image incremental
        """
        
        self.img.image(self.target_name)
        self.assertEqual(3, len(self.mock_executor_class.call_args_list))
        self.assertEqual(3, len(self.mock_executor.method_calls))
    
    def test__prepare_image(self):
        """
        test prepare image
        """
        
        self.img._prepare_image(self.target_name)
        self.assertEqual( 
            ((["si_prepareclient", 
               "--server " + imager._remove_domain(self.image_server_name), 
               "--yes"],), {}),
            self.mock_executor_class.call_args_list[0])
        #self.assertEqual(
            #("ssh_run", (self.target_name, "root"), {"check": True}), 
            #self.mock_executor.method_calls[0])
    
    def test__get_image(self):
        """
        test get image
        """
        
        image_name = "image_name"
        ip_assignment = "REPLICANT"
        self.img._get_image(self.target_name, image_name, ip_assignment)
        self.assertEqual( 
            ((["si_getimage", 
               "--golden-client " + imager._remove_domain(self.target_name), 
               "--image " + image_name, 
               "--quiet", 
               "--update-script YES", 
               "--ip-assignment REPLICANT", 
               "--post-install reboot"],), {}),
            self.mock_executor_class.call_args_list[0])
        self.assertEqual(
            ("ssh_run", (self.image_server_name, "root"), {"check": True}), 
            self.mock_executor.method_calls[0])
    
    def test__create_default_image_name(self):
        target = 'target'
        format = '%Y_%m_%d_%H_%M'
        str_date = '2009_04_06_13_00'
        image_name = self.img._create_default_image_name(target, time.strptime(str_date, format))
        self.assertEqual(image_name, 'target.2009_04_06_13_00')
    
    def test_create_image(self):
        """
        test create image
        """
        
        self.img.create_image(self.target_name)
        self.assertEqual(2, len(self.mock_executor_class.call_args_list))
        #self.assertEqual(2, len(self.mock_executor.method_calls))
        self.assertEqual(1, len(self.mock_executor.method_calls))
    
class RemoveDomainTestCase(unittest.TestCase):
    """
    """
    
    def test_remove_domain(self):
        self.assertEqual("omega", imager._remove_domain("omega.ucd.ie"))
    
    def test_remove_domain_empty(self):
        self.assertEqual("", imager._remove_domain(""))
    
    def test_remove_domain_no_dots(self):
        self.assertEqual("omega", imager._remove_domain("omega"))


def suite():
    return unittest.makeSuite([ImagerTestCase, RemoveDomainTestCase])

if __name__ == '__main__':
    unittest.main()
