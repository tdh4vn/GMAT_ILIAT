//
//  MainViewController.h
//  GMAT
//
//  Created by Trung Đức on 3/14/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface MainViewController : UIViewController<UITabBarDelegate, UITableViewDataSource>

@property (weak, nonatomic) IBOutlet UITableView *tbvQuestionPack;

@end
