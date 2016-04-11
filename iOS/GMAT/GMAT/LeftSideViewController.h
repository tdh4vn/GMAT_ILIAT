//
//  LeftSideViewController.h
//  GMAT
//
//  Created by Trung Đức on 4/6/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface LeftSideViewController : UIViewController <UITableViewDelegate, UITableViewDataSource>

@property (weak, nonatomic) IBOutlet UITableView *tbvLeftSide;

@end
