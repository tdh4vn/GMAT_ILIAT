//
//  LoginViewController.m
//  GMAT
//
//  Created by Trung Đức on 3/12/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import "LoginViewController.h"
#import "Constant.h"
#import "MainViewController.h"
#import "AppDelegate.h"

@interface LoginViewController ()

@end

@implementation LoginViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    _imvLoginBackGround.image = [UIImage imageNamed:kLoginBackGroundImage];
    _imvLoginAreBackground.image = [UIImage imageNamed:kLoginAreaBackgroundImage];
    _imvLogo.image = [UIImage imageNamed:kLogoImage];
    
    
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc]
                                   initWithTarget:self
                                   action:@selector(dismissKeyboard)];
    
    [self.view addGestureRecognizer:tap];
    
    [self.txtEmail setValue:[UIColor whiteColor]
                    forKeyPath:@"_placeholderLabel.textColor"];
    [self.txtPassword setValue:[UIColor whiteColor]
                 forKeyPath:@"_placeholderLabel.textColor"];
    
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
    
    MainViewController *mainViewController = [self.storyboard instantiateViewControllerWithIdentifier:@"mainView"];
    
    UINavigationController *navigationController = [[UINavigationController alloc] initWithRootViewController:mainViewController];
    
    AppDelegate *appDelegate = (AppDelegate *)[[UIApplication sharedApplication] delegate];
    
    
    [self presentViewController:navigationController animated:YES completion:^{
        
        [appDelegate.window setRootViewController:navigationController];
        
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
