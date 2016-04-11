//
//  LoginViewController.m
//  GMAT
//
//  Created by Trung Đức on 3/12/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import "LoginViewController.h"
#import "Constant.h"
#import "AppDelegate.h"
#import "MainViewController.h"
#import "LeftSideViewController.h"
#import "MMDrawerController.h"

#define kPlaceHolerLabelTexColorKeyPath                 @"_placeholderLabel.textColor"

@interface LoginViewController ()



@end

@implementation LoginViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    _imvLoginBackGround.image = [UIImage imageNamed:kImage_LoginBackGround];
    _imvLoginAreBackground.image = [UIImage imageNamed:kImage_LoginAreaBackground];
    _imvLogo.image = [UIImage imageNamed:kImage_Logo];
    
    
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc]
                                   initWithTarget:self
                                   action:@selector(dismissKeyboard)];
    
    [self.view addGestureRecognizer:tap];
    
    [self.txtEmail setValue:[UIColor whiteColor]
                    forKeyPath:kPlaceHolerLabelTexColorKeyPath];
    [self.txtPassword setValue:[UIColor whiteColor]
                 forKeyPath:kPlaceHolerLabelTexColorKeyPath];
    
    _txtEmail.delegate = self;
    _txtPassword.delegate = self;
    
    CATransition* transitionLogo = [CATransition animation];
    transitionLogo.duration = 1.3f;
    transitionLogo.type = kCATransitionMoveIn;
    transitionLogo.subtype = kCATransitionFromBottom;
    [_imvLogo.layer addAnimation:transitionLogo forKey:kCATransition];
    
}

#pragma mark - TextField Delegate

- (void)textFieldDidEndEditing:(UITextField *)textField{
    if (textField == _txtEmail) {
        
        [self dismissKeyboard];
        
    } else {
        
        [self dismissKeyboard];
        
    }
}


#pragma mark - Class Funtion

-(void)dismissKeyboard {
    [self.txtEmail resignFirstResponder];
    [self.txtPassword resignFirstResponder];

}
- (IBAction)btnLoginDidTouch:(id)sender {
    
    MainViewController *mainViewController = [MainViewController sharedInstance];
    UINavigationController *navigationController = [[UINavigationController alloc] initWithRootViewController:mainViewController];
    
    LeftSideViewController * leftSideViewController = [self.storyboard instantiateViewControllerWithIdentifier:@"leftSide"];
    
    
    
    
    //Create and config drawerController
    
    self.drawerController = [[MMDrawerController alloc]
                             initWithCenterViewController:navigationController
                             leftDrawerViewController:leftSideViewController];
    [self.drawerController setShowsShadow:YES];
    [self.drawerController setMaximumLeftDrawerWidth:kWidth_LeftSide];
    [self.drawerController setOpenDrawerGestureModeMask:MMOpenDrawerGestureModeAll];
    [self.drawerController setCloseDrawerGestureModeMask:MMCloseDrawerGestureModeAll];
    
    
    
    
    [self presentViewController:_drawerController animated:YES completion:^{
        
        AppDelegate *appDelegate = (AppDelegate *)[[UIApplication sharedApplication] delegate];
        [appDelegate.window setRootViewController:self.drawerController];
        
    }];
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
