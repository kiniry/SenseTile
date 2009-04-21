# -*- coding: utf-8 -*-

import mock
import unittest

from sensetile import imager

class ImagerTestCase(unittest.TestCase):
    """
    """

    def test__create(self):
        """
        image creation
        """
        
        image_server_name = "image_server"
        img = imager.Imager(image_server_name)
        self.failIfEqual(None, imager)
    
    def test__image_incremental_update(self):
        """
        incremental image target
        """
        
        image_server_name = "image_server.ucd.ie"
        target_name = "target.ucd.ie"
        fake_return = (0, "", "")
        mock_executor = mock.Mock( methods = "ssh_run" )
        mock_executor_class = mock.Mock()
        mock_executor_class.return_value = mock_executor
        img = imager.Imager(image_server_name, executor_class = mock_executor_class)
        img._image_incremental_update(target_name)
        self.assertEqual( 
            ((["si_updateclient", "--server " + imager._remove_domain(image_server_name), "--yes"],), {}),
            mock_executor_class.call_args_list[0])
        self.assertEqual(
            ("ssh_run", (target_name, "root"), {"check": True}), 
            mock_executor.method_calls[0])
    
    def test__image_complete_update(self):
        """
        complete image target
        """
        
        image_server_name = "image_server.ucd.ie"
        target_name = "target.ucd.ie"
        fake_return = (0, "", "")
        mock_executor = mock.Mock( methods = "ssh_run" )
        mock_executor_class = mock.Mock()
        mock_executor_class.return_value = mock_executor
        img = imager.Imager(image_server_name, executor_class = mock_executor_class)
        img._image_complete_update(target_name)
        self.assertEqual( 
            ((["si_updateclient", 
               "--server " + imager._remove_domain(image_server_name), 
               "--yes", 
               "--autoinstall"],), 
             {}),
            mock_executor_class.call_args_list[0])
        self.assertEqual(
            ("ssh_run", (target_name, "root"), {"check": True}), 
            mock_executor.method_calls[0])
    
    def test__push_overrides(self):
        """
        push overrides on target
        """
        
        image_server_name = "image_server.ucd.ie"
        target_name = "target.ucd.ie"
        fake_return = (0, "", "")
        mock_executor = mock.Mock( methods = "ssh_run" )
        mock_executor_class = mock.Mock()
        mock_executor_class.return_value = mock_executor
        img = imager.Imager(image_server_name, executor_class = mock_executor_class)
        img._push_overrides(target_name)
        self.assertEqual( 
            ((["si_pushoverrides", imager._remove_domain(target_name)],), {}),
            mock_executor_class.call_args_list[0])
        self.assertEqual(
            ("ssh_run", (image_server_name, "root"), {"check": True}), 
            mock_executor.method_calls[0])
    
    def test__update_grub_menu(self):
        """
        test upgrade grub menu
        """
        
        image_server_name = "image_server.ucd.ie"
        target_name = "target.ucd.ie"
        fake_return = (0, "", "")
        mock_executor = mock.Mock( methods = "ssh_run" )
        mock_executor_class = mock.Mock()
        mock_executor_class.return_value = mock_executor
        img = imager.Imager(image_server_name, executor_class = mock_executor_class)
        img._update_grub_menu(target_name)
        self.assertEqual(1, len(mock_executor_class.call_args_list))
        self.assertEqual(
            ("ssh_run", (target_name, "root"), {"check": True}), 
            mock_executor.method_calls[0])
    
    def test__reboot(self):
        """
        test reboot
        """
        
        image_server_name = "image_server.ucd.ie"
        target_name = "target.ucd.ie"
        fake_return = (0, "", "")
        mock_executor = mock.Mock( methods = "ssh_run" )
        mock_executor_class = mock.Mock()
        mock_executor_class.return_value = mock_executor
        img = imager.Imager(image_server_name, executor_class = mock_executor_class)
        img._reboot(target_name)
        self.assertEqual(1, len(mock_executor_class.call_args_list))
        self.assertEqual(
            ("ssh_run", (target_name, "root"), {"check": True}), 
            mock_executor.method_calls[0])
    
    def test_image_incremental(self):
        """
        test image incremental
        """
        
        image_server_name = "image_server.ucd.ie"
        target_name = "target.ucd.ie"
        fake_return = (0, "", "")
        mock_executor = mock.Mock( methods = "ssh_run" )
        mock_executor_class = mock.Mock()
        mock_executor_class.return_value = mock_executor
        img = imager.Imager(image_server_name, executor_class = mock_executor_class)
        img.image(target_name)
        self.assertEqual(3, len(mock_executor_class.call_args_list))
        self.assertEqual(3, len(mock_executor.method_calls))
    
    def test_image_complete(self):
        """
        test image complete
        """
        
        image_server_name = "image_server.ucd.ie"
        target_name = "target.ucd.ie"
        fake_return = (0, "", "")
        mock_executor = mock.Mock( methods = "ssh_run" )
        mock_executor_class = mock.Mock()
        mock_executor_class.return_value = mock_executor
        img = imager.Imager(image_server_name, executor_class = mock_executor_class)
        img.image(target_name, autoinstall = True)
        self.assertEqual(2, len(mock_executor_class.call_args_list))
        self.assertEqual(2, len(mock_executor.method_calls))

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
