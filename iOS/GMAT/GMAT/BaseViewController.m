//
//  BaseViewController.m
//  GMAT
//
//  Created by Trung Đức on 4/6/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import "BaseViewController.h"
#import "Constant.h"

@implementation BaseViewController

- (void)viewWillAppear:(BOOL)animated {
    
    [super viewWillAppear:animated];
    
    self.navigationController.navigationBar.titleTextAttributes = @{NSForegroundColorAttributeName: kColor_Window};
    
}

@end
