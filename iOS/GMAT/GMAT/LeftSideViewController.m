//
//  LeftSideViewController.m
//  GMAT
//
//  Created by Trung Đức on 4/6/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import "LeftSideViewController.h"
#import "Constant.h"
#import "UIViewController+MMDrawerController.h"
#import "MainViewController.h"
#import "BaseCell.h"

@implementation LeftSideViewController

- (void)viewDidLoad {
    
    [super viewDidLoad];
    
    _tbvLeftSide.tableFooterView = [[UIView alloc]init];
    _tbvLeftSide.alwaysBounceVertical = NO;
    
}

#pragma mark - TableView DataSource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 2;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    if (section == 0) {
        return 1;
    } else if (section == 1) {
        return 1;
    } else {
        return 0;
    }
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    static NSString *cellId = @"leftSideCell";
    
    UITableViewCell *cell = [[UITableViewCell alloc]initWithStyle:UITableViewCellStyleDefault reuseIdentifier:cellId];
    
    if (indexPath.section == 0) {
        
        cell.imageView.image = [UIImage imageNamed:kImage_Logo];
        cell.imageView.contentMode = UIViewContentModeScaleAspectFit;
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        
    } else if (indexPath.section == 1) {
        
        cell.textLabel.text = @"Hello";
        
    } else {
        
    }
    
    
    //fix left margin
    
    if ([cell respondsToSelector:@selector(setSeparatorInset:)]) {
        cell.separatorInset = UIEdgeInsetsMake(0, 0, 0, 0);
    }
    if ([cell respondsToSelector:@selector(setPreservesSuperviewLayoutMargins:)]) {
        cell.preservesSuperviewLayoutMargins = NO;
    }
    if ([cell respondsToSelector:@selector(setLayoutMargins:)]) {
        cell.layoutMargins = UIEdgeInsetsMake(0, 0, 0, 0);
    }
    
    return cell;
}

#pragma mark - TableView Delegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    
    [tableView deselectRowAtIndexPath:indexPath animated:NO];
    
    if (indexPath.section == 1) {
        
        MainViewController *mainViewController = [MainViewController sharedInstance];
        UINavigationController *navigationController = [[UINavigationController alloc] initWithRootViewController:mainViewController];
        
        [self.mm_drawerController setCenterViewController:navigationController withCloseAnimation:YES completion:^(BOOL finished) {
            
        }];
        
    }

}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    if (indexPath.section == 0) {
        return 160;
    } else if (indexPath.section == 1) {
        return 50;
    } else {
        return 50;
    }
}

@end
